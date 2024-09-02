package com.pokemon.company.pokemon_joute.dto;

public class DresseurCreateDto {
    // ici on n'ajoute surtout pas l'id qui est gérée par la base de données

    private String pseudo;
    private String motDePasse;

    // constructeurs

    public DresseurCreateDto(String pseudo, String motDePasse) {
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;
    }

    public DresseurCreateDto() {
    }

// getters et setters

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}