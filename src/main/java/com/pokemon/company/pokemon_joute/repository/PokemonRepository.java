package com.pokemon.company.pokemon_joute.repository;

import com.pokemon.company.pokemon_joute.model.Pokemon;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends ListCrudRepository<Pokemon, Long> {
    boolean existsByEspeceId(Long especeId);
}
