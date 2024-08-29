package com.pokemon.company.pokemon_joute.repository;

import com.pokemon.company.pokemon_joute.model.Dresseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DresseurRepository extends JpaRepository<Dresseur, Long> {
}
