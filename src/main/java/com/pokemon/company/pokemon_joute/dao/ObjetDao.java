package com.pokemon.company.pokemon_joute.dao;

import com.pokemon.company.pokemon_joute.model.Objet;
import org.springframework.data.repository.CrudRepository;

public interface ObjetDao extends CrudRepository<Objet, Long> {
}