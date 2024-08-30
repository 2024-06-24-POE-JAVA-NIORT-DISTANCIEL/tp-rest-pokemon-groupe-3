package com.pokemon.company.pokemon_joute.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "pokemon")
public class Pokemon {

    // attributs
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private int niveau;

    @Column(nullable = true)
    private int experience;

    @Column(nullable = true)
    private int pv;
    private int pvMax;

    @ManyToOne
    @JoinColumn(name = "espece_id", nullable = false)
    private Espece espece;

    @ManyToOne
    @JoinColumn(name = "dresseur_id", nullable = true)
    private Dresseur dresseur;

    @ManyToMany
    @JoinTable(
            name = "pokemon_attaque",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "attaque_id")
    )
    private List<Attaque> attaques;

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
        return "PokemonDto : {" +
                "id = " + id +
                ", nom = " + nom + '\'' +
                ", niveau = " + niveau +
                ", experience = " + experience +
                ", pv = " + pv +
                ", pvMax = " + pvMax +
                '}';
    }
}
