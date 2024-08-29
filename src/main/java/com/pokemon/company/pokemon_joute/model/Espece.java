package com.pokemon.company.pokemon_joute.model;

import com.pokemon.company.pokemon_joute.utils.EnumType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Espece {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nom;
	
	private EnumType type;
	
	private Integer pvInitial;
	
	//private Attaque attaqueInitiale;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public EnumType getType() {
		return type;
	}

	public void setType(EnumType type) {
		this.type = type;
	}

	public Integer getPvInitial() {
		return pvInitial;
	}

	public void setPvInitial(Integer pvInitial) {
		this.pvInitial = pvInitial;
	}





	
	
}
