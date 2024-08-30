package com.pokemon.company.pokemon_joute.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "inventaire")
public class Inventaire {

    // attributs

    @Id
    @ManyToOne
    @JoinColumn(name = "dresseur_id")
    private Dresseur dresseur;

    @Id
    @ManyToOne
    @JoinColumn(name = "objet_id")
    private Objet objet;

    @Column(nullable = false)
    @NotNull(message = "La quantité ne peut pas être Null")
    @Min(value = 0, message = "La quantité ne peut pas être négative")
    private int quantite;

    // constructeurs
    // laisser vide sauf cas particulier,
    // dans ce cas, ajouter le constructeur par défaut sans paramètres

    // getters et setters

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
        return "Inventaire{" +
                "dresseur=" + dresseur.getId() +
                ", objet=" + objet.getId() +
                ", quantite=" + quantite +
                '}';
    }
}

