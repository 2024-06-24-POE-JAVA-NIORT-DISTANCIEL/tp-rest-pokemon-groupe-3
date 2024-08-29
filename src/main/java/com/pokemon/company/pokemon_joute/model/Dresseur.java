package com.pokemon.company.pokemon_joute.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "dresseur")
public class Dresseur {

    // attributs

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String pseudo;

    private String motDePasse;

    @Column(nullable = true)
    private Integer portefeuille; // ici le choix d'Integer plutôt que int reflète le nullable

    @OneToMany(mappedBy = "dresseur")
    private List<Pokemon> pokemons;

    @OneToMany(mappedBy = "dresseur")
    private List<Inventaire> inventaires;

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

    public Integer getPortefeuille() {
        return portefeuille;
    }

    public void setPortefeuille(Integer portefeuille) {
        this.portefeuille = portefeuille;
    }

    public List<Inventaire> getInventaires() {
        return inventaires;
    }

    public void setInventaires(List<Inventaire> inventaires) {
        this.inventaires = inventaires;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
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
