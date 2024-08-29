package com.pokemon.company.pokemon_joute;

import com.pokemon.company.pokemon_joute.dao.PokemonDao;
import com.pokemon.company.pokemon_joute.model.Pokemon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PokemonTest {

    @Autowired
    PokemonDao pokemonDao;

    @Test
    public void testFindById() {

        Pokemon salameche = new Pokemon();
        salameche.setNom("Salamèche");
        salameche.setPv(35);
        salameche.setPvMax(35);
        salameche.setExperience(0);
        salameche.setNiveau(0);

        pokemonDao.save(salameche);
        System.out.println(salameche);

        Optional<Pokemon> salamecheFindById = pokemonDao.findById(salameche.getId());
        Assertions.assertNotNull(salamecheFindById, "Le pokémon salamèche devrait être dans la BDD");

    }

    @Test
    public void testSave() {

        Pokemon carapuce = new Pokemon();
        carapuce.setNom("carapuce");
        carapuce.setPv(35);
        carapuce.setPvMax(35);
        carapuce.setNiveau(0);
        carapuce.setExperience(0);

        pokemonDao.save(carapuce);

        Assertions.assertNotNull(carapuce, "Un pokémon devrait être enregistré !");
        System.out.println(carapuce);
    }

    @Test
    public void testDeleteById() {

        Pokemon bulbizarre = new Pokemon();
        bulbizarre.setNom("bulbizarre");
        bulbizarre.setPv(35);
        bulbizarre.setPvMax(35);
        bulbizarre.setNiveau(0);
        bulbizarre.setExperience(0);

        Pokemon savedPokemon = pokemonDao.save(bulbizarre);

        pokemonDao.deleteById(savedPokemon.getId());

        Optional<Pokemon> deletedPokemon = pokemonDao.findById(savedPokemon.getId());

        Assertions.assertFalse(deletedPokemon.isPresent(), "Le pokémon devrait être supprimé");

    }

    @Test
    public void testFindAll() {

        Pokemon salameche = new Pokemon();
        salameche.setNom("Salamèche");
        salameche.setPv(35);
        salameche.setPvMax(35);
        salameche.setExperience(0);
        salameche.setNiveau(0);

        pokemonDao.save(salameche);

        Pokemon carapuce = new Pokemon();
        carapuce.setNom("carapuce");
        carapuce.setPv(35);
        carapuce.setPvMax(35);
        carapuce.setNiveau(0);
        carapuce.setExperience(0);

        pokemonDao.save(carapuce);

        pokemonDao.findAll();


    }

    @Test
    public void findByNomContainingIgnoreCaseOrderByNomDesc() {

        Pokemon salameche = new Pokemon();
        salameche.setNom("Salamèche");
        salameche.setPv(35);
        salameche.setPvMax(35);
        salameche.setExperience(0);
        salameche.setNiveau(0);

        Pokemon savedPokemon = pokemonDao.save(salameche);

        List<Pokemon> pokemonList = pokemonDao.findByNomContainingIgnoreCaseOrderByNomDesc("aLa");

        Assertions.assertFalse(pokemonList.isEmpty(), "herlolo");


    }
}
