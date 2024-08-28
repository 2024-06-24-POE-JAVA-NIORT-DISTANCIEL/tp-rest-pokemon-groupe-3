package com.pokemon.company.pokemon_joute.dao;

import com.pokemon.company.pokemon_joute.model.Attaque;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttaqueDao extends CrudRepository<Attaque, Long> {
}
