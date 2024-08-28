package com.pokemon.company.pokemon_joute.dao;

import com.pokemon.company.pokemon_joute.model.Pokemon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonDao extends CrudRepository<Pokemon, Long> {
}
