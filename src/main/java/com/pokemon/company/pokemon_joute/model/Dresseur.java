package com.pokemon.company.pokemon_joute.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Dresseur {

    // attributs

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String pseudo;

    private String motDePasse;
    private Integer portefeuille;
    private List<Objet> inventaire;


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

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Integer getPortefeuille() {
        return portefeuille;
    }

    public void setPortefeuille(Integer portefeuille) {
        this.portefeuille = portefeuille;
    }

    public List<Objet> getInventaire() {
        return inventaire;
    }

    public void setInventaire(List<Objet> inventaire) {
        this.inventaire = inventaire;
    }

    // méthodes (si nécessaire)

}
