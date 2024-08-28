package com.pokemon.company.pokemon_joute.repository;

import com.pokemon.company.pokemon_joute.model.Objet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjetRepository extends CrudRepository<Objet, Long> {
}
