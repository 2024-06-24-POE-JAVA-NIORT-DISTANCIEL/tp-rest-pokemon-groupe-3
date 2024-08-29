package com.pokemon.company.pokemon_joute.model;

import jakarta.persistence.*;

@Entity
public class Inventaire {

    // attributs

    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "dresseur_id")
        private Dresseur dresseur;

        @ManyToOne
        @JoinColumn(name = "objet_id")
        private Objet objet;

        private int quantite;


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
}

