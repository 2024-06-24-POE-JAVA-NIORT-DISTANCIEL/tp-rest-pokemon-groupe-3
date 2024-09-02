package com.pokemon.company.pokemon_joute.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pokemon.company.pokemon_joute.model.Espece;
import com.pokemon.company.pokemon_joute.utils.Type;


public interface EspeceDao extends CrudRepository<Espece, Long> {

    // Recherche Espece par nom
    List<Espece> findByNom(String nom);

    // Recherche Espece par type
    List<Espece> findByType(Type type);

    // Recherche par pvInitial
    List<Espece> findByPvInitial(Integer pvInitial);

    // Recherche par attaque
    // List<Espece> findByAttaque(Attaque attaque);

}