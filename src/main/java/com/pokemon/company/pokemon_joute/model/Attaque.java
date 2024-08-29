package com.pokemon.company.pokemon_joute.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Attaque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
