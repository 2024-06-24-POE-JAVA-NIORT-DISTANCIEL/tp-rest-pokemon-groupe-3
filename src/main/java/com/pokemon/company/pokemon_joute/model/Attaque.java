package com.pokemon.company.pokemon_joute.model;

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

 @Override
 public String toString() {
 return "Attaque{" +
 "id=" + id +
 ", nom='" + nom + '\'' +
 '}';
}

// méthodes (si nécessaire)

}
