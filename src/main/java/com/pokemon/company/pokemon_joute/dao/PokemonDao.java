package com.pokemon.company.pokemon_joute.dao;

import com.pokemon.company.pokemon_joute.model.Pokemon;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PokemonDao extends CrudRepository<Pokemon, Long> {
    List<Pokemon> findByNomContainingIgnoreCaseOrderByNomDesc(String nom);
}