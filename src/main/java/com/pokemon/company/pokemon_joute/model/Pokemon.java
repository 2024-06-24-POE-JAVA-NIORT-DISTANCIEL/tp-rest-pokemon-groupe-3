package com.pokemon.company.pokemon_joute.model;

import jakarta.persistence.*;

@Entity
public class Pokemon {

    // attributs
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private int niveau;
    private int experience;
    private int pv;
    private int pvMax;

    @ManyToOne
    @JoinColumn(name = "ESPECE_ID")
    private Espece espece;

    // constructeurs
    // Laisser vide sauf cas particulier,
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

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getPvMax() {
        return pvMax;
    }

    public void setPvMax(int pvMax) {
        this.pvMax = pvMax;
    }

    public Espece getEspece() {
        return espece;
    }

    public void setEspece(Espece espece) {
        this.espece = espece;
    }

    // méthodes (si nécessaire)

    @Override
    public String toString() {
        return "Pokemon : {" +
                "id = " + id +
                ", nom = " + nom + '\'' +
                ", niveau = " + niveau +
                ", experience = " + experience +
                ", pv = " + pv +
                ", pvMax = " + pvMax +
                '}';
    }
}
