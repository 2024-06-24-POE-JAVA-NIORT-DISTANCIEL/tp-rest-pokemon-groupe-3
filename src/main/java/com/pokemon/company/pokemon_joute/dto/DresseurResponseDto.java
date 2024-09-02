package com.pokemon.company.pokemon_joute.dto;

public class DresseurResponseDto {
    private Long id;
    private String pseudo;
    private Integer portefeuille;

    // constructeurs

    public DresseurResponseDto(Long id, String pseudo, Integer portefeuille) {
        this.id = id;
        this.pseudo = pseudo;
        this.portefeuille = portefeuille;
    }

    public DresseurResponseDto() {
    }

    // getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Integer getPortefeuille() {
        return portefeuille;
    }

    public void setPortefeuille(Integer portefeuille) {
        this.portefeuille = portefeuille;
    }
}