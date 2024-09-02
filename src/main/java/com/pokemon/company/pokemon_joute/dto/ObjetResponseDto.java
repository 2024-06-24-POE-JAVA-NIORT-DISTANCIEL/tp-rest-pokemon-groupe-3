package com.pokemon.company.pokemon_joute.dto;

import com.pokemon.company.pokemon_joute.utils.TypeObjet;

public class ObjetResponseDto {
    // ici on n'ajoute surtout pas l'id qui est gérée par la base de données

    private Long id;
    private String nom;
    private int prix;
    private TypeObjet typeObjet;

    // constructeurs

    public ObjetResponseDto(Long id, String nom, int prix, TypeObjet typeObjet) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.typeObjet = typeObjet;
    }

    public ObjetResponseDto() {
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

    public TypeObjet getTypeObjet() {
        return typeObjet;
    }

    public void setTypeObjet(TypeObjet typeObjet) {
        this.typeObjet = typeObjet;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
}