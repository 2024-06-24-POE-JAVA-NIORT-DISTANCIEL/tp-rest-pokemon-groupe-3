package com.pokemon.company.pokemon_joute.dto;

import com.pokemon.company.pokemon_joute.model.DresseurObjet;
import com.pokemon.company.pokemon_joute.model.Pokemon;

import java.util.List;

public class DresseurDto {

    private String pseudo;
    private Integer porteFeuille;
    private List<Pokemon> pokemons;
    private List<DresseurObjet> dresseurObjets;

    // constructeurs

    public DresseurDto(String pseudo, Integer porteFeuille, List<Pokemon> pokemons, List<DresseurObjet> dresseurObjets) {
        this.pseudo = pseudo;
        this.porteFeuille = porteFeuille;
        this.pokemons = pokemons;
        this.dresseurObjets = dresseurObjets;
    }

    public DresseurDto() {
    }

    // getters et setters

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Integer getPorteFeuille() {
        return porteFeuille;
    }

    public void setPorteFeuille(Integer porteFeuille) {
        this.porteFeuille = porteFeuille;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public List<DresseurObjet> getDresseurObjets() {
        return dresseurObjets;
    }

    public void setDresseurObjets(List<DresseurObjet> dresseurObjets) {
        this.dresseurObjets = dresseurObjets;
    }
}
