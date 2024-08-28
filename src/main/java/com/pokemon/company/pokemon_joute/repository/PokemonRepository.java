package com.pokemon.company.pokemon_joute.repository;

import com.pokemon.company.pokemon_joute.model.Pokemon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends CrudRepository<Pokemon, Long> {
}
