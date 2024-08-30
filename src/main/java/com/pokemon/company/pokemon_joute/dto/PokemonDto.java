package com.pokemon.company.pokemon_joute.dto;

public class PokemonDto {

    private Long id;
    private String nom;
    private int niveau;
    private int experience;
    private int pv;
    private int pvMax;

    private Long espece_id;

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

    public Long getEspece_id() {
        return espece_id;
    }

    public void setEspece_id(Long espece_id) {
        this.espece_id = espece_id;
    }
}
