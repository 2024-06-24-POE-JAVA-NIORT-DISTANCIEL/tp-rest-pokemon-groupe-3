package com.pokemon.company.pokemon_joute.dao;

import com.pokemon.company.pokemon_joute.model.Attaque;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttaqueDao extends CrudRepository<Attaque, Long> {

        /**
         * Recherche toutes les attaques par leur nom.
         */
        List<Attaque> findByNom(String nom);

        /**
         * Recherche toutes les attaques par leur nom, sans tenir compte de la casse.
         */
        List<Attaque> findByNomIgnoreCase(String nom);

        /**
         * Recherche toutes les attaques contenant une chaîne de caractères dans leur nom, sans tenir compte de la casse.
         */
        List<Attaque> findByNomContainingIgnoreCase(String nom);

    }

