package com.pokemon.company.pokemon_joute.repository;

import com.pokemon.company.pokemon_joute.model.Objet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjetRepository extends JpaRepository<Objet, Long> {
}
