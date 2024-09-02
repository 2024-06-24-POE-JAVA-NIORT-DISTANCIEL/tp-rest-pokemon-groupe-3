package com.pokemon.company.pokemon_joute.model;

import com.pokemon.company.pokemon_joute.utils.TypeObjet;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "objet")
public class Objet {

    // attributs

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private int prix;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TypeObjet typeObjet;

    @OneToMany(mappedBy = "objet")
    private List<DresseurObjet> dresseurs;

    // constructeurs

    public Objet(String nom, int prix, TypeObjet typeObjet) {
        this.nom = nom;
        this.prix = prix;
        this.typeObjet = typeObjet;
    }

    public Objet() {
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

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public TypeObjet getTypeObjet() {
        return typeObjet;
    }

    public void setTypeObjet(TypeObjet typeObjet) {
        this.typeObjet = typeObjet;
    }

    // méthodes (si nécessaire)

    @Override
    public String toString() {
        return "Objet{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix='" + prix + '\'' +
                ", type=" + typeObjet +
                '}';
    }
}