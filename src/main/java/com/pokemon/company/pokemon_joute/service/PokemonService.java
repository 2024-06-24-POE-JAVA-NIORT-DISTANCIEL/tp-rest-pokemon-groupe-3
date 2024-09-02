package com.pokemon.company.pokemon_joute.service;

import com.pokemon.company.pokemon_joute.dao.*;
import com.pokemon.company.pokemon_joute.dto.*;
import com.pokemon.company.pokemon_joute.model.*;
import com.pokemon.company.pokemon_joute.repository.*;
import com.pokemon.company.pokemon_joute.utils.LogDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(Pokemon.class));

    private LogDetails logDetails = new LogDetails();

    @Autowired
    PokemonDao pokemonDao;

    @Autowired
    PokemonRepository pokemonRepository;

    @Autowired
    EspeceDao especeDao;

    @Autowired
    EspeceRepository especeRepository;

    @Transactional
    public PokemonResponseDto save(PokemonCreateDto pokemonCreateDto) {
        // le Service passe par les DTO pour ne pas manipuler directement les entités

        // on récupère l'id fournie par le DTO (donc venant du JSON)
        Long especeId = pokemonCreateDto.getEspeceId();

        // on utilise le Repository pour accéder à la base Postgres
        // pour une opération courante comme findById
        // et on vérifie si l'espèce existe
        Espece espece = especeRepository.findById(especeId)
                .orElseThrow(() -> new IllegalArgumentException("Aucune espece ne possede l'id: " + especeId));

        // on crée une Entité pour pouvoir associer l'entité Espece créée précédemment
        // pour ça on la fabrique à l'aide du DTO
        Pokemon pokemon = new Pokemon(pokemonCreateDto.getNom(), espece);

        // on réalise l'association proprement dite
        pokemon.setEspece(espece);

        // on sauvegarde cette entité via le repository
        // qui se charge des opérations courantes (extends CrudRepository)
        Pokemon savedPokemon = pokemonRepository.save(pokemon);

        LOGGER.info("Le pokemon '" + pokemon.getNom() + "' est sauvegarde");
        logDetails.single(pokemon);

        // on renvoie le DTO Réponse de cette entité au RestController
        return toPokemonResponseDto(Optional.of(savedPokemon));
    }

    public PokemonResponseDto findById(Long id) {
        return findById(id, false);
    }

    public PokemonResponseDto findById(Long id, Boolean disableLog) {
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aucun pokemon trouvé avec l'identifiant: " + id));
        if (!disableLog) {
            LOGGER.info("Le pokemon '" + pokemon.getNom() + "' est trouve par son identifiant: " + id);
            logDetails.single(pokemon);
        }

        return toPokemonResponseDto(pokemonRepository.findById(id));
    }

    public void deleteById(Long id) {
        try {
            pokemonDao.deleteById(id);
            LOGGER.info("Le pokemon '" + pokemonRepository.findById(id) + "' a ete supprime par son identifiant");
        } catch (EmptyResultDataAccessException e) {
            System.err.println("Erreur : Aucun pokemon trouve avec l'ID " + id);
        }
    }

    public List<PokemonResponseDto> findAll() {
        List<Pokemon> pokemons = pokemonRepository.findAll();
        LOGGER.info("Tous les pokemons sont recuperes (" + pokemons.size() + " pokemon(s))");

        return pokemonRepository.findAll().stream()
                .map(pokemon -> toPokemonResponseDto(Optional.of(pokemon)))
                .collect(Collectors.toList());
    }

    public Iterable<Pokemon> findByNomContainingIgnoreCaseOrderByNomDesc(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            LOGGER.info("Le nom du pokemon n'est pas dans la base de donnee");
            return this.pokemonRepository.findAll();
        }
        LOGGER.info("La liste des pokemons par nom dans l'ordre descendant");
        return pokemonDao.findByNomContainingIgnoreCaseOrderByNomDesc(nom);
    }

    private PokemonDto toDto(Optional<Pokemon> pokemon) {
        if (pokemon.isEmpty()) {
            return null;
        }
        PokemonDto dto = new PokemonDto();
        dto.setNom(pokemon.get().getNom());
        dto.setNiveau(pokemon.get().getNiveau());
        dto.setPv(pokemon.get().getPv());
        dto.setPvMax(pokemon.get().getPvMax());
        dto.setExperience(pokemon.get().getExperience());
        dto.setEspeceId(pokemon.get().getEspece().getId());
        return dto;
    }

    private Pokemon toEntity(PokemonDto pokemonDto) {
        if (pokemonDto == null) {
            return null;
        }
        Pokemon entity = new Pokemon();
        entity.setNom(pokemonDto.getNom());
        entity.setNiveau(pokemonDto.getNiveau());
        entity.setPv(pokemonDto.getPv());
        entity.setPvMax(pokemonDto.getPvMax());
        entity.setExperience(pokemonDto.getExperience());
        Optional<Espece> espece = especeDao.findById(pokemonDto.getEspeceId());
        if (espece.isEmpty()) {
            return null;
        }
        entity.setEspece(espece.get());
        return entity;
    }

    public PokemonResponseDto toPokemonResponseDto(Optional<Pokemon> pokemon) {
        if (pokemon.isEmpty()) {
            return null;
        }
        PokemonResponseDto dto = new PokemonResponseDto();
        dto.setId(pokemon.get().getId());
        dto.setNom(pokemon.get().getNom());
        dto.setNiveau(pokemon.get().getNiveau());
        dto.setExperience(pokemon.get().getExperience());
        dto.setPv(pokemon.get().getPv());
        dto.setPvMax(pokemon.get().getPvMax());
        dto.setEspeceId(pokemon.get().getEspece().getId());

        // on récupère la Liste d'objets Attaques du Pokemon et on les place dans un flux (stream)
        dto.setAttaqueIds(pokemon.get().getAttaques().stream()

                // pour chacune d'entre elle, on récupère l'id et on remap le flux avec une référence de méthode
                .map(Attaque::getId)
                // on remet tout ça dans une Liste pour remplir la liste d'id d'attaques du dto réponse
                .collect(Collectors.toList()));

        return dto;
    }
}