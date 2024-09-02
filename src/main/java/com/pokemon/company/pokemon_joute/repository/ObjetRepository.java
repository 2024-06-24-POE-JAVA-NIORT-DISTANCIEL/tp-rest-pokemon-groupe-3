package com.pokemon.company.pokemon_joute.repository;

import com.pokemon.company.pokemon_joute.model.Objet;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// on utilise JpaRepository pour récupérer des List (et pas des Iterable)
// JpaRepository étendant ListCrudRepository
@Repository
public interface ObjetRepository extends ListCrudRepository<Objet, Long> {
    // query methods, prix étant un attribut de Objet
    List<Objet> findByPrixLessThanEqual(Integer maxPrix);

    List<Objet> findByPrixGreaterThanEqual(Integer minPrix);

    List<Objet> findByPrixBetween(Integer minPrix, Integer maxPrix);
}
