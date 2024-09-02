package com.pokemon.company.pokemon_joute.dto;

import java.util.List;

public class PokemonResponseDto {
    // ici l'id générée est connue et intégrée au DTO dans la réponse

    private Long id;
    private String nom;
    private int niveau;
    private int experience;
    private int pv;
    private int pvMax;
    private Long especeId;
    private List<Long> attaqueIds;

    // constructeurs

    public PokemonResponseDto(Long id, String nom, int niveau, int experience, int pv, int pvMax, Long especeId, List<Long> attaqueIds) {
        this.id = id;
        this.nom = nom;
        this.niveau = niveau;
        this.experience = experience;
        this.pv = pv;
        this.pvMax = pvMax;
        this.especeId = especeId;
        this.attaqueIds = attaqueIds;
    }

    public PokemonResponseDto() {
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

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getPvMax() {
        return pvMax;
    }

    public void setPvMax(int pvMax) {
        this.pvMax = pvMax;
    }

    public Long getEspeceId() {
        return especeId;
    }

    public void setEspeceId(Long especeId) {
        this.especeId = especeId;
    }

    public List<Long> getAttaqueIds() {
        return attaqueIds;
    }

    public void setAttaqueIds(List<Long> attaqueIds) {
        this.attaqueIds = attaqueIds;
    }
}