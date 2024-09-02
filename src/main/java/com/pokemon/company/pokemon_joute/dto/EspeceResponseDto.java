package com.pokemon.company.pokemon_joute.dto;

import com.pokemon.company.pokemon_joute.utils.Type;

public class EspeceResponseDto {
    // ici l'id générée est connue et intégrée au DTO dans la réponse

    private Long id;
    private String nom;
    private Type type;
    private int pvInitial;
    private Long attaqueInitialeId;

    // constructeurs

    public EspeceResponseDto(Long id, String nom, Type type, int pvInitial, Long attaqueInitialeId) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.pvInitial = pvInitial;
        this.attaqueInitialeId = attaqueInitialeId;
    }

    public EspeceResponseDto() {
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