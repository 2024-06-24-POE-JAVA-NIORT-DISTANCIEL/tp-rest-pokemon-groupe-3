package com.pokemon.company.pokemon_joute.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pokemon.company.pokemon_joute.model.Espece;
import com.pokemon.company.pokemon_joute.utils.EnumType;


@Repository
public interface EspeceDao extends CrudRepository<Espece, Long> {
	
	// Recherche Espece par nom
	public List<Espece> findByNom(String nom);
	
	// Recherche Espece par type
	public List<Espece> findByType(EnumType type);
	
	// Recherche par pvInitial
	public List<Espece> findByPvInitial(Integer pvInitial);
	
	// Recherche par attaque
	//public List<Espece> findByAttaque(Attaque attaque);
	
}
