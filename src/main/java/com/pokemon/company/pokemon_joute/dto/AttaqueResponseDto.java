package com.pokemon.company.pokemon_joute.dto;

import com.pokemon.company.pokemon_joute.utils.Type;

public class AttaqueResponseDto {
    // ici l'id générée est connue et intégrée au DTO dans la réponse

    private Long id;
    private String nom;
    private Type type;
    private int degats;

    // constructeurs

    public AttaqueResponseDto(Long id, String nom, Type type, int degats) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.degats = degats;
    }

    public AttaqueResponseDto() {
    }

    // getters et setters

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getDegats() {
        return degats;
    }

    public void setDegats(int degats) {
        this.degats = degats;
    }
}