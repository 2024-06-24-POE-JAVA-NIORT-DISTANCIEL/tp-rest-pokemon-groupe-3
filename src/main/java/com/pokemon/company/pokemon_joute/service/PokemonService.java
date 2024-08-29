package com.pokemon.company.pokemon_joute.service;

import com.pokemon.company.pokemon_joute.dao.EspeceDao;
import com.pokemon.company.pokemon_joute.dao.PokemonDao;
import com.pokemon.company.pokemon_joute.dto.PokemonDto;
import com.pokemon.company.pokemon_joute.model.Espece;
import com.pokemon.company.pokemon_joute.model.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PokemonService {

    @Autowired
    PokemonDao pokemonDao;

    @Autowired
    EspeceDao especeDao;

    public PokemonDto findById(Long id) {
        return toDto(this.pokemonDao.findById(id));
    }

    @Transactional
    public PokemonDto save(Pokemon pokemon) {
        return toDto(Optional.of(pokemonDao.save(pokemon)));
    }

    public void deleteById(Long id) {
        try {
            pokemonDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.err.println("Erreur : Aucun Pokémon trouvé avec l'ID " + id);
        }
    }

    public Iterable<Pokemon> findAll() {
        return this.pokemonDao.findAll();
    }

    public Iterable<Pokemon> findByNomContainingIgnoreCaseOrderByNomDesc(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            return this.pokemonDao.findAll();
        }
        return pokemonDao.findByNomContainingIgnoreCaseOrderByNomDesc(nom);
    }

    private PokemonDto toDto(Optional<Pokemon> pokemon) {
        if (pokemon.isEmpty()) {
            return null;
        }
        PokemonDto dto = new PokemonDto();
        dto.setId(pokemon.get().getId());
        dto.setNom(pokemon.get().getNom());
        dto.setNiveau(pokemon.get().getNiveau());
        dto.setPv(pokemon.get().getPv());
        dto.setPvMax(pokemon.get().getPvMax());
        dto.setExperience(pokemon.get().getExperience());
        dto.setEspece_id(pokemon.get().getEspece().getId());
        return dto;
    }

    private Pokemon toEntity(PokemonDto pokemonDto) {
        if (pokemonDto == null) {
            return null;
        }
        Pokemon entity = new Pokemon();
        entity.setId(pokemonDto.getId());
        entity.setNom(pokemonDto.getNom());
        entity.setNiveau(pokemonDto.getNiveau());
        entity.setPv(pokemonDto.getPv());
        entity.setPvMax(pokemonDto.getPvMax());
        entity.setExperience(pokemonDto.getExperience());
        Optional<Espece> espece = especeDao.findById(pokemonDto.getEspece_id());
        if (espece.isEmpty()) {
            return null;
        }
        entity.setEspece(espece.get());
        return entity;
    }


}
