package com.pokemon.company.pokemon_joute.dto;

public class PokemonCreateDto {
    // ici on n'ajoute surtout pas l'id qui est gérée par la base de données

    private String nom;
    private Long especeId;

    // constructeurs

    public PokemonCreateDto(String nom, Long especeId) {
        this.nom = nom;
        this.especeId = especeId;
    }

    public PokemonCreateDto() {
    }

    // getters et setters

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getEspeceId() {
        return especeId;
    }

    public void setEspeceId(Long especeId) {
        this.especeId = especeId;
    }
}