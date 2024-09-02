package com.pokemon.company.pokemon_joute.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pokemon")
public class Pokemon {

    // attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private int niveau = 1;

    private int experience = 0;

    private int pv;
    private int pvMax;

    @ManyToOne
    @JoinColumn(name = "espece_id", nullable = false)
    private Espece espece;

    @ManyToOne
    @JoinColumn(name = "dresseur_id", nullable = true)
    private Dresseur dresseur;

    @ManyToMany
    @JsonManagedReference // annotation TRES IMPORTANTE : elle permet d'éviter une boucle infinie des entités
                          // qui se référencent les unes les autres pokemon>espece>attaque>pokemon>espece>....
                          // elle fonctionne de pair avec @JsonBackReference dans Pokemon
    @JoinTable(
            name = "pokemon_attaque",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "attaque_id")
    )
    private List<Attaque> attaques = new ArrayList<>(); // ici on initialise une liste vide
                                                        // on commencera à la remplir dans le constructeur
                                                        // avec l'attaque initiale de l'espèce

    // constructeurs

    public Pokemon(String nom, Espece espece) {
        // on récupère les pv de l'espèce pour initialiser ceux du pokemon créé
        // les autres attributs sont réglés par défaut dans leur déclaration
        int pv = espece.getPvInitial();

        this.nom = nom;
        this.pv = pv;
        this.pvMax = pv;
        this.espece = espece;

        // on récupère l'attaque initiale de l'espece pour initialiser la liste d'attaques du pokemon avec
        if (espece.getAttaqueInitiale() != null) {
            this.attaques.add(espece.getAttaqueInitiale());
        }
    }

    public Pokemon() {
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

    public Dresseur getDresseur() {
        return dresseur;
    }

    public void setDresseur(Dresseur dresseur) {
        this.dresseur = dresseur;
    }

    public List<Attaque> getAttaques() {
        return attaques;
    }

    public void setAttaques(List<Attaque> attaques) {
        this.attaques = attaques;
    }

    // méthodes (si nécessaire)

    @Override
    public String toString() {
        return "Pokemon{" +
                "id = " + id +
                ", nom = " + nom + '\'' +
                ", niveau = " + niveau +
                ", experience = " + experience +
                ", pv = " + pv +
                ", pvMax = " + pvMax +
                '}';
    }
}