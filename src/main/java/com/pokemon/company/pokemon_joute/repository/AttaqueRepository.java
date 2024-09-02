package com.pokemon.company.pokemon_joute.repository;

import com.pokemon.company.pokemon_joute.model.Attaque;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttaqueRepository extends ListCrudRepository<Attaque, Long> {
}