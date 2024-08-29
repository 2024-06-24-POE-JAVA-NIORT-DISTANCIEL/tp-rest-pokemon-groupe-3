package com.pokemon.company.pokemon_joute.model;

import com.pokemon.company.pokemon_joute.utils.Type;
import jakarta.persistence.*;

@Entity
@Table(name = "espece")
public class Espece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @Enumerated(EnumType.STRING)
	private Type type;

    private int pvInitial;

    @ManyToOne
    @JoinColumn(name = "attaque_initiale_id")
    private Attaque attaqueInitiale;

    // constructeurs

    // laisser vide sauf cas particulier,
    // dans ce cas, ajouter le constructeur par défaut sans paramètres

    //private Attaque attaqueInitiale;

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getPvInitial() {
        return pvInitial;
    }

    public void setPvInitial(Integer pvInitial) {
        this.pvInitial = pvInitial;
    }

    public Attaque getAttaqueInitiale() {
        return attaqueInitiale;
    }

    public void setAttaqueInitiale(Attaque attaqueInitiale) {
        this.attaqueInitiale = attaqueInitiale;
    }

    // méthodes (si nécessaire)

    @Override
    public String toString() {
        return "Espece{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", pvInitial=" + pvInitial +
                ", attaqueInitiale=" + attaqueInitiale +
                ", type=" + type +
                '}';
    }

}
