package com.pokemon.company.pokemon_joute.model;

import com.pokemon.company.pokemon_joute.utils.Type;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "attaque")
public class Attaque {

    // attributs

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @Enumerated(EnumType.STRING)
    private Type type;

    private int degats;

    @ManyToMany(mappedBy = "attaques")
    private List<Pokemon> pokemons;

    // private PokemonDto pokemon; // Relation @ManyToOne avec Pokémon, voir plus tard

    // constructeurs
    // laisser vide sauf cas particulier,
    // dans ce cas, ajouter le constructeur par défaut sans paramètres

    // private PokemonDto pokemon; // Relation @ManyToOne avec Pokémon, voir plus tard

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getDegats() {
        return degats;
    }

    public void setDegats(int degats) {
        this.degats = degats;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    // méthodes (si nécessaire)

    @Override
    public String toString() {
        return "Attaque{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", type=" + type +
                ", degats=" + degats +
                '}';
    }

}
