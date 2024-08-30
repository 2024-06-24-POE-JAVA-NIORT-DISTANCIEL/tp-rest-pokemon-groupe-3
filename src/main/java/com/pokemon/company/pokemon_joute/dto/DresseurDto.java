package com.pokemon.company.pokemon_joute.dto;

import com.pokemon.company.pokemon_joute.model.Inventaire;
import com.pokemon.company.pokemon_joute.model.Pokemon;

import java.util.List;

public class DresseurDto {

    private String pseudo;
    private Integer porteFeuille;
    private List<Pokemon> pokemons;
    private List<Inventaire> inventaires;

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

    public List<Inventaire> getInventaires() {
        return inventaires;
    }

    public void setInventaires(List<Inventaire> inventaires) {
        this.inventaires = inventaires;
    }
}
