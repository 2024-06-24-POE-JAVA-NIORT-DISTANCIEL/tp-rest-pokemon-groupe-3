package com.pokemon.company.pokemon_joute.service;

import com.pokemon.company.pokemon_joute.dao.PokemonDao;
import com.pokemon.company.pokemon_joute.model.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PokemonService {

    @Autowired
    PokemonDao pokemonDao;

    public Optional<Pokemon> findById(Long id) {
        return this.pokemonDao.findById(id);
    }

    public Pokemon save(Pokemon pokemon) {
        return pokemonDao.save(pokemon);
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
}
