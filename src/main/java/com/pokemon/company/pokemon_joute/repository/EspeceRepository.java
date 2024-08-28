package com.pokemon.company.pokemon_joute.repository;

import com.pokemon.company.pokemon_joute.model.Espece;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspeceRepository extends CrudRepository<Espece, Long> {
}
