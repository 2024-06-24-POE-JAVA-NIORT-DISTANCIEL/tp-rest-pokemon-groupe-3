package com.pokemon.company.pokemon_joute.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "dresseur_objet")
public class DresseurObjet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // attributs

    @ManyToOne
    @JoinColumn(name = "dresseur_id")
    private Dresseur dresseur;

    @ManyToOne
    @JoinColumn(name = "objet_id")
    private Objet objet;

    @Column(nullable = false)
    @NotNull(message = "La quantité ne peut pas être Null")
    @Min(value = 0, message = "La quantité ne peut pas être négative")
    private int quantite;

    // constructeurs

    public DresseurObjet(Dresseur dresseur, Objet objet, int quantite) {
        this.dresseur = dresseur;
        this.objet = objet;
        this.quantite = quantite;
    }

    public DresseurObjet() {
    }

    // getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dresseur getDresseur() {
        return dresseur;
    }

    public void setDresseur(Dresseur dresseur) {
        this.dresseur = dresseur;
    }

    public Objet getObjet() {
        return objet;
    }

    public void setObjet(Objet objet) {
        this.objet = objet;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    // méthodes (si nécessaire)

    @Override
    public String toString() {
        return "DresseurObjet{" +
                "dresseur=" + dresseur.getId() +
                ", objet=" + objet.getId() +
                ", quantite=" + quantite +
                '}';
    }
}