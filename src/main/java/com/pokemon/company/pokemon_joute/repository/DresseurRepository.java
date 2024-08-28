package com.pokemon.company.pokemon_joute.repository;

import com.pokemon.company.pokemon_joute.model.Dresseur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DresseurRepository extends CrudRepository<Dresseur, Long> {
}
