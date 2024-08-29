package com.pokemon.company.pokemon_joute.model;

import com.pokemon.company.pokemon_joute.utils.TypeObjet;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Objet {

    // attributs

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private int prix;

    @Enumerated(EnumType.STRING)
    @Column(name = "type" , nullable = false)
    private TypeObjet type;

    @OneToMany(mappedBy = "objet")
    private List<Inventaire> dresseurs;

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

    public TypeObjet getType() {
        return type;
    }

    public void setType(TypeObjet type) {
        this.type = type;
    }

    // méthodes (si nécessaire)

    @Override
    public String toString() {
        return "Objet{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix='" + prix + '\'' +
                ", type=" + type +
                '}';
    }
}
