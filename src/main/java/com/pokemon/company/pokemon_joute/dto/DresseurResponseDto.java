package com.pokemon.company.pokemon_joute.dto;

public class DresseurResponseDto {
    private Long id;
    private String pseudonyme;
    private Integer montantPortefeuille;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPseudonyme() {
        return pseudonyme;
    }

    public void setPseudonyme(String pseudonyme) {
        this.pseudonyme = pseudonyme;
    }

    public Integer getMontantPortefeuille() {
        return montantPortefeuille;
    }

    public void setMontantPortefeuille(Integer montantPortefeuille) {
        this.montantPortefeuille = montantPortefeuille;
    }
}
