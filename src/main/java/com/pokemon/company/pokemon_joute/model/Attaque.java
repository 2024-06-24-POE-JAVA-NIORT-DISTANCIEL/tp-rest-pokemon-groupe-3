package com.pokemon.company.pokemon_joute.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference // annotation TRES IMPORTANTE : elle permet d'éviter une boucle infinie des entités
                       // qui se référencent les unes les autres pokemon>espece>attaque>pokemon>espece>....
                       // elle fonctionne de pair avec @JsonManagedReference dans Pokemon
    private List<Pokemon> pokemons;

    // constructeurs

    public Attaque(String nom, Type type, int degats) {
        this.nom = nom;
        this.type = type;
        this.degats = degats;
    }

    public Attaque() {
    }

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