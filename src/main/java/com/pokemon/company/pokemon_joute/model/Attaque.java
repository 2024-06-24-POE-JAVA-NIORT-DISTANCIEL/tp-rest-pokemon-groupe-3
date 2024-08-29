package com.pokemon.company.pokemon_joute.model;

import com.pokemon.company.pokemon_joute.utils.Type;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Attaque {

    // attributs

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    private Type type;
    private int degats;

    // private Pokemon pokemon; // Relation @ManyToOne avec Pokémon, voir plus tard

    // constructeurs
    // laisser vide sauf cas particulier,
    // dans ce cas, ajouter le constructeur par défaut sans paramètres

    // private Pokemon pokemon; // Relation @ManyToOne avec Pokémon, voir plus tard

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

    @Override
    public String toString() {
        return "Attaque{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }

// méthodes (si nécessaire)

}
