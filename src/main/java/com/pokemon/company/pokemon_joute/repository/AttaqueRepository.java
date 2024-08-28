package com.pokemon.company.pokemon_joute.repository;

import com.pokemon.company.pokemon_joute.model.Attaque;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttaqueRepository extends CrudRepository<Attaque, Long> {
}
