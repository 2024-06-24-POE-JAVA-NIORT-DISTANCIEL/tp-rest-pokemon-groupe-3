package com.pokemon.company.pokemon_joute.model;

import com.pokemon.company.pokemon_joute.utils.TypeObjet;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Objet {

    // attributs

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private Integer prix;
    private TypeObjet type;


    // constructeurs
    // laisser vide sauf cas particulier,
    // dans ce cas, ajouter le constructeur par défaut sans paramètres


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

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public TypeObjet getType() {
        return type;
    }

    public void setType(TypeObjet type) {
        this.type = type;
    }

    // méthodes (si nécessaire)

}
