package com.pokemon.company.pokemon_joute.rest.controller;

import com.pokemon.company.pokemon_joute.dto.AttaqueResponseDto;
import com.pokemon.company.pokemon_joute.dto.PokemonCreateDto;
import com.pokemon.company.pokemon_joute.dto.PokemonResponseDto;
import com.pokemon.company.pokemon_joute.mapper.AttaqueMapper;
import com.pokemon.company.pokemon_joute.mapper.PokemonMapper;
import com.pokemon.company.pokemon_joute.model.Pokemon;
import com.pokemon.company.pokemon_joute.service.AttaqueService;
import com.pokemon.company.pokemon_joute.service.PokemonService;
import com.pokemon.company.pokemon_joute.utils.LogDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
public class PokemonRestController {

    private final static Logger LOGGER = Logger.getLogger(String.valueOf(PokemonRestController.class));

    private LogDetails logDetails = new LogDetails();

    @Autowired
    private PokemonService pokemonService;

    @Autowired
    private PokemonMapper pokemonMapper;

    @Autowired
    private AttaqueService attaqueService;

    @Autowired
    private AttaqueMapper attaqueMapper;

    @PostMapping("/pokemons")
    public ResponseEntity<PokemonResponseDto> save(@RequestBody PokemonCreateDto pokemonCreateDto) {
        // @RequestBody : on récupère le Json et Spring Data le convertit en DTO

        // on sauvegarde ce DTO en faisant appel au service et on récupère un ResponseDTO
        PokemonResponseDto pokemonResponseDto = pokemonService.save(pokemonCreateDto);

        // on renvoie dans le corps de la requête réponse le ResponseDTO nouvellement sauvegardé
        return ResponseEntity.status(HttpStatus.CREATED).body(pokemonResponseDto);
    }

    @GetMapping("/pokemons/{id}")
    public ResponseEntity<PokemonResponseDto> findById(@PathVariable("id") Long id) {
        try {
            PokemonResponseDto pokemonResponseDto = pokemonService.findById(id);
            return ResponseEntity.ok(pokemonResponseDto);
        } catch (IllegalArgumentException e) {
            LOGGER.info("PokemonRestController a leve une exception " + e + " pour argument illegal: " + id);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/pokemons/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        this.pokemonService.deleteById(id);
    }

    @GetMapping("/pokemons/tous")
    public ResponseEntity<List<PokemonResponseDto>> findAll() {
        List<PokemonResponseDto> pokemonResponseDtos = pokemonService.findAll();

        if (pokemonResponseDtos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // ici, on remap les pokemonsResponseDtos (une liste de pokemonResponseDto) en pokemons (une liste de Pokemons)
        // pour pouver envoyer cette liste à logDetails et afficher les détails dans la console
        List<Pokemon> pokemons = pokemonResponseDtos.stream()
                .map(pokemonMapper::toPokemon) // Utilise le mapper (du package mapper) pour convertir chaque DTO en Pokemon
                .collect(Collectors.toList());
        logDetails.list(pokemons);

        return ResponseEntity.ok(pokemonResponseDtos);
    }

    @GetMapping("/pokemons/trouveParNom")
    public ResponseEntity<Iterable<Pokemon>> findByNomContainingIgnoreCaseOrderByNomDesc(@RequestParam(required = false) String nom) {
        Iterable<Pokemon> pokemons = pokemonService.findByNomContainingIgnoreCaseOrderByNomDesc(nom);
        return ResponseEntity.ok(pokemons);
    }

    @GetMapping("/combats")
    public ResponseEntity<Pokemon> combatsDePokemon(
            @RequestParam(value = "pokemonId", required = false) Long id1,
            @RequestParam(value = "pokemonId", required = false) Long id2
    ) {
        PokemonResponseDto pokemonResponseDto1 = pokemonService.findById(id1);
        PokemonResponseDto pokemonResponseDto2 = pokemonService.findById(id2);

        if (pokemonResponseDto1.getPv() == 0 && pokemonResponseDto2.getPv() == 0) {
            LOGGER.info("On ne peut pas faire combattre un pokemon qui a 0 point de vie !");
            return ResponseEntity.badRequest().build();
        }

        Random random = new Random();
        Long randomId = random.nextBoolean() ? id1 : id2;
        pokemonResponseDto1.setPv(pokemonResponseDto1.getPvMax());
        pokemonResponseDto2.setPv(pokemonResponseDto2.getPvMax());

        Pokemon combattantA;
        Pokemon combattantB;

        if (Objects.equals(randomId, id1)) {
            combattantA = pokemonMapper.toPokemon(pokemonResponseDto1);
            combattantB = pokemonMapper.toPokemon(pokemonResponseDto2);
        } else {
            combattantB = pokemonMapper.toPokemon(pokemonResponseDto2);
            combattantA = pokemonMapper.toPokemon(pokemonResponseDto1);
        }

        while (true) {
            List<Long> listeIds1 = pokemonResponseDto1.getAttaqueIds();

            Random random1 = new Random();
            int randomIndex = random1.nextInt(listeIds1.size()); // Génère un index aléatoire
            Long randomId1 = listeIds1.get(randomIndex); // Récupère l'id de l'attaque tirée au hasard

            AttaqueResponseDto attaqueAleatoire = attaqueService.findById(randomId1);
            LOGGER.info(combattantA.getNom() + " utilise : ");
            logDetails.single(attaqueAleatoire.getNom());

            PokemonResponseDto pokemonBBlesse = pokemonService.utilise(attaqueMapper.toAttaque(attaqueAleatoire), pokemonMapper.toPokemonResponseDto(combattantB), combattantA.getNiveau());

            combattantB = pokemonMapper.toPokemon(pokemonBBlesse);
            if (combattantB.getPv() == 0) {
                finDeCombat(combattantA, combattantB);
                break;
            }

            List<Long> listeIds2 = pokemonResponseDto2.getAttaqueIds();

            Random random2 = new Random();
            int randomIndex2 = random2.nextInt(listeIds2.size()); // Génère un index aléatoire
            Long randomId2 = listeIds1.get(randomIndex2); // Récupère l'id de l'attaque tirée au hasard

            AttaqueResponseDto attaqueAleatoire2 = attaqueService.findById(randomId2);
            LOGGER.info(combattantA.getNom() + " utilise : ");
            logDetails.single(attaqueAleatoire2.getNom());

            PokemonResponseDto pokemonABlesse = pokemonService.utilise(attaqueMapper.toAttaque(attaqueAleatoire2), pokemonMapper.toPokemonResponseDto(combattantA), combattantB.getNiveau());

            combattantA = pokemonMapper.toPokemon(pokemonABlesse);
            if (combattantA.getPv() == 0) {
                finDeCombat(combattantB, combattantA);
                break;
            }
        }
        return null;
    }

    private void finDeCombat(Pokemon pokemonGagnant, Pokemon pokemonPerdant) {

        int experienceGagnee = pokemonPerdant.getNiveau() * 4;

        LOGGER.info(pokemonPerdant.getNom() + " est K.O. Il ne peut plus combattre... ");
        LOGGER.info(pokemonGagnant.getNom() + " gagne " + experienceGagnee + " point d'expérience ");

        int ancienXp = pokemonGagnant.getExperience();
        int xpRestante = ancienXp % 5;
        int surplusXp = xpRestante + experienceGagnee;
        int niveauxGagnes = surplusXp / 5;

        // On met à jour l'expérience du pokémon après une victoire
        pokemonGagnant.setExperience(pokemonGagnant.getExperience() + experienceGagnee);

        // On met à jour les niveaux
        LOGGER.info(niveauxGagnes + " niveau(x) gagné(s) ");
        pokemonGagnant.setNiveau(pokemonGagnant.getNiveau() + niveauxGagnes);

        LOGGER.info(" Le pokémon à gagné +10 pv max par niveau gagné ");
        pokemonGagnant.setPvMax(pokemonGagnant.getPvMax() + 10 * niveauxGagnes);

    }


}
