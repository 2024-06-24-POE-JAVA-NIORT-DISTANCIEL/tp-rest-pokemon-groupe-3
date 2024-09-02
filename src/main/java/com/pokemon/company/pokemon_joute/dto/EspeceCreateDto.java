package com.pokemon.company.pokemon_joute.dto;

import com.pokemon.company.pokemon_joute.utils.Type;

public class EspeceCreateDto {
    // ici on n'ajoute surtout pas l'id qui est gérée par la base de données

    private String nom;
    private Type type;
    private int pvInitial;
    private Long attaqueInitialeId;

    // constructeurs

    public EspeceCreateDto(String nom, Type type, int pvInitial, Long attaqueInitialeId) {
        this.nom = nom;
        this.type = type;
        this.pvInitial = pvInitial;
        this.attaqueInitialeId = attaqueInitialeId;
    }

    public EspeceCreateDto() {
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

    public int getPvInitial() {
        return pvInitial;
    }

    public void setPvInitial(int pvInitial) {
        this.pvInitial = pvInitial;
    }

    public Long getAttaqueInitialeId() {
        return attaqueInitialeId;
    }

    public void setAttaqueInitialeId(Long attaqueInitialeId) {
        this.attaqueInitialeId = attaqueInitialeId;
    }
}