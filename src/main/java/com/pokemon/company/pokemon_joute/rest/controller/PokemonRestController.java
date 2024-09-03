package com.pokemon.company.pokemon_joute.rest.controller;

import com.pokemon.company.pokemon_joute.dto.PokemonCreateDto;
import com.pokemon.company.pokemon_joute.dto.PokemonResponseDto;
import com.pokemon.company.pokemon_joute.exception.PokemonCombatException;
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
    public ResponseEntity<String> combatsDePokemon(
            @RequestParam(value = "pokemonId1", required = false) Long id1,
            @RequestParam(value = "pokemonId2", required = false) Long id2) {
        // le code métier qui réalise effectivement le combat est exécuté par le service
        try {
            pokemonService.combatsDePokemon(id1, id2);
            return ResponseEntity.ok().build();
        } catch (PokemonCombatException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
