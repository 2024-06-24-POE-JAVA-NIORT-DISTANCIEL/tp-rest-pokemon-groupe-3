package com.pokemon.company.pokemon_joute.dto;

import com.pokemon.company.pokemon_joute.utils.Type;

public class AttaqueCreateDto {
    // ici on n'ajoute surtout pas l'id qui est gérée par la base de données

    private String nom;
    private Type type;
    private int degats;

    // constructeurs

    public AttaqueCreateDto(String nom, Type type, int degats) {
        this.nom = nom;
        this.type = type;
        this.degats = degats;
    }

    public AttaqueCreateDto() {
    }

    // getters et setters

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