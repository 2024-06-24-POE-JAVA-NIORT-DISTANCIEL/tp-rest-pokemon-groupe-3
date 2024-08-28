package com.pokemon.company.pokemon_joute.dao;

import com.pokemon.company.pokemon_joute.model.Espece;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspeceDao extends CrudRepository<Espece, Long> {
}
