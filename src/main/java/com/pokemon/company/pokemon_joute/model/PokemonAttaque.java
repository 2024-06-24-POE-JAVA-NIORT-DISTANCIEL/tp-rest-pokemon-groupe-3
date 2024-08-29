package com.pokemon.company.pokemon_joute.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pokemon_attaque")
public class PokemonAttaque {

    @Id
    @ManyToOne
    @JoinColumn(name = "pokemon_id")
    private Pokemon pokemon;

    @Id
    @ManyToOne
    @JoinColumn(name = "attaque_id")
    private Attaque attaque;

    // constructeurs
    // Laisser vide sauf cas particulier,
    // dans ce cas, ajouter le constructeur par défaut sans paramètres

    // getters et setters

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public Attaque getAttaque() {
        return attaque;
    }

    public void setAttaque(Attaque attaque) {
        this.attaque = attaque;
    }

    // méthodes (si nécessaire)

}
