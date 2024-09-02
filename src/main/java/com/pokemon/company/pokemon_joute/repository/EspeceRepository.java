package com.pokemon.company.pokemon_joute.repository;

import com.pokemon.company.pokemon_joute.model.Espece;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspeceRepository extends ListCrudRepository<Espece, Long> {
}