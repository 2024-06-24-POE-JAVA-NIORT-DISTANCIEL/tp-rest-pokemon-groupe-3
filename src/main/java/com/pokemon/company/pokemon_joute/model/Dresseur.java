package com.pokemon.company.pokemon_joute.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

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

    private int portefeuille = 100;

    @OneToMany(mappedBy = "dresseur")
    private List<Pokemon> pokemons;

    @OneToMany(mappedBy = "dresseur")
    private List<DresseurObjet> dresseurObjets;

    // constructeurs
    // laisser vide sauf cas particulier,
    // dans ce cas, ajouter le constructeur par défaut sans paramètres

    public Dresseur(String pseudo, String motDePasse) {
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;
    }

    public Dresseur() {
    }

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

    public int getPortefeuille() {
        return portefeuille;
    }

    public void setPortefeuille(int portefeuille) {
        this.portefeuille = portefeuille;
    }

    public List<DresseurObjet> getDresseurObjets() {
        return dresseurObjets;
    }

    public void setDresseurObjets(List<DresseurObjet> dresseurObjets) {
        this.dresseurObjets = dresseurObjets;
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

    @Transient
    public List<Objet> getInventaire() {
        return dresseurObjets.stream()
                .map(DresseurObjet::getObjet)
                .collect(Collectors.toList());
    }
}
