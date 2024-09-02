package com.pokemon.company.pokemon_joute.dto;

public class PokemonDto {

    private String nom;
    private int niveau;
    private int experience;
    private int pv;
    private int pvMax;
    private Long especeId;

    // constructeurs

    public PokemonDto(String nom, int niveau, int experience, int pv, int pvMax, Long especeId) {
        this.nom = nom;
        this.niveau = niveau;
        this.experience = experience;
        this.pv = pv;
        this.pvMax = pvMax;
        this.especeId = especeId;
    }

    public PokemonDto() {
    }

// getters et setters

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
}