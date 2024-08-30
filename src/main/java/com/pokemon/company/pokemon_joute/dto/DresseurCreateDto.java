package com.pokemon.company.pokemon_joute.dto;

public class DresseurCreateDto {
    private String pseudonyme;
    private String motDePasse;
    private Integer montantPortefeuille;

    public String getPseudonyme() {
        return pseudonyme;
    }

    public void setPseudonyme(String pseudonyme) {
        this.pseudonyme = pseudonyme;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Integer getMontantPortefeuille() {
        return montantPortefeuille;
    }

    public void setMontantPortefeuille(Integer montantPortefeuille) {
        this.montantPortefeuille = montantPortefeuille;
    }
}
