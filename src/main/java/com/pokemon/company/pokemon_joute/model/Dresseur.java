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
    private int portefeuille;

    @OneToMany(mappedBy = "dresseur")
    private List<Inventaire> inventaire;

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

    public int getPortefeuille() {
        return portefeuille;
    }

    public void setPortefeuille(int portefeuille) {
        this.portefeuille = portefeuille;
    }

    public List<Inventaire> getInventaire() {
        return inventaire;
    }

    public void setInventaire(List<Inventaire> inventaire) {
        this.inventaire = inventaire;
    }

    // méthodes (si nécessaire)

    @Override
    public String toString() {
        return "Dresseur{" +
                "id=" + id +
                ", pseudo='" + pseudo + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", portefeuille=" + portefeuille +
                '}';
    }
}
