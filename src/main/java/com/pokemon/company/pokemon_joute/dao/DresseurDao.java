package com.pokemon.company.pokemon_joute.dao;

import com.pokemon.company.pokemon_joute.model.Dresseur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DresseurDao extends CrudRepository<Dresseur, Long> {


}
