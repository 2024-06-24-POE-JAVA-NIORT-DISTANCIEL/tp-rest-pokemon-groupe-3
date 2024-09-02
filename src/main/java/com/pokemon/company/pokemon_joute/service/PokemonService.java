package com.pokemon.company.pokemon_joute.service;

import com.pokemon.company.pokemon_joute.dao.*;
import com.pokemon.company.pokemon_joute.dto.*;
import com.pokemon.company.pokemon_joute.model.*;
import com.pokemon.company.pokemon_joute.repository.*;
import com.pokemon.company.pokemon_joute.utils.LogDetails;
import com.pokemon.company.pokemon_joute.utils.Type;
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

    @Autowired
    EspeceService especeService;

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

    public PokemonResponseDto utilise(Attaque attaque, PokemonResponseDto pokemonResponseDto, int niveauAttaquant) {

        double nbPvPerdu;

        double[][] modificateursAttaque = new double[5][5];

        // 0 = air
        modificateursAttaque[0][0] = 1;
        modificateursAttaque[0][1] = 0.7;
        modificateursAttaque[0][2] = 1;
        modificateursAttaque[0][3] = 1.3;
        modificateursAttaque[0][4] = 1;

        // 1 = eau
        modificateursAttaque[1][0] = 1.3;
        modificateursAttaque[1][1] = 1;
        modificateursAttaque[1][2] = 0.7;
        modificateursAttaque[1][3] = 1;
        modificateursAttaque[1][4] = 1.3;

        // 2 = insecte
        modificateursAttaque[2][0] = 1;
        modificateursAttaque[2][1] = 1.3;
        modificateursAttaque[2][2] = 1;
        modificateursAttaque[2][3] = 0.7;
        modificateursAttaque[2][4] = 0.7;

        // 3 = plante
        modificateursAttaque[3][0] = 0.7;
        modificateursAttaque[3][1] = 1;
        modificateursAttaque[3][2] = 1.3;
        modificateursAttaque[3][3] = 1;
        modificateursAttaque[3][4] = 0.7;

        // 4 = feu
        modificateursAttaque[4][0] = 1;
        modificateursAttaque[4][1] = 0.7;
        modificateursAttaque[4][2] = 1.3;
        modificateursAttaque[4][3] = 1.3;
        modificateursAttaque[4][4] = 0.7;

        EspeceResponseDto espece = especeService.findById(pokemonResponseDto.getEspeceId());
        Type typeEspece = espece.getType();
        Type typeAttaque = attaque.getType();

        int indexTypeEspece;
        int indexTypeAttaque;

        switch (typeEspece) {
            case VOL -> indexTypeEspece = 0;
            case EAU -> indexTypeEspece = 1;
            case INSECTE -> indexTypeEspece = 2;
            case PLANTE -> indexTypeEspece = 3;
            case FEU -> indexTypeEspece = 4;
            default -> indexTypeEspece = 1_000;
        }

        switch (typeAttaque) {
            case VOL -> indexTypeAttaque = 0;
            case EAU -> indexTypeAttaque = 1;
            case INSECTE -> indexTypeAttaque = 2;
            case PLANTE -> indexTypeAttaque = 3;
            case FEU -> indexTypeAttaque = 4;
            default -> indexTypeAttaque = 1_000;
        }

        double modificateurAttaque = modificateursAttaque[indexTypeAttaque][indexTypeEspece];
        nbPvPerdu = ((double) niveauAttaquant / 10) * attaque.getDegats() * modificateurAttaque;

        if (modificateurAttaque < 1) {
            LOGGER.info("Ce n’est pas très efficace ...");
        }

        if (modificateurAttaque > 1) {
            LOGGER.info("C’est super efficace ! ");
        }

        double nouveauPv = pokemonResponseDto.getPv() - nbPvPerdu;
        if (nouveauPv < 0) {
            nouveauPv = 0;
        }
        pokemonResponseDto.setPv((int) (nouveauPv));

        return pokemonResponseDto;
    }
}