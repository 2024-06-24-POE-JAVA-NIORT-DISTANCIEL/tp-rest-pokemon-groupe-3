package com.pokemon.company.pokemon_joute.rest.controller;

import com.pokemon.company.pokemon_joute.dto.*;
import com.pokemon.company.pokemon_joute.mapper.PokemonMapper;
import com.pokemon.company.pokemon_joute.model.Pokemon;
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
@RequestMapping("/pokemons")
public class PokemonRestController {

    private final static Logger LOGGER = Logger.getLogger(String.valueOf(PokemonRestController.class));

    private LogDetails logDetails = new LogDetails();

    @Autowired
    private PokemonService pokemonService;

    @Autowired
    private PokemonMapper pokemonMapper;

    @PostMapping
    public ResponseEntity<PokemonResponseDto> save(@RequestBody PokemonCreateDto pokemonCreateDto) {
        // @RequestBody : on récupère le Json et Spring Data le convertit en DTO

        // on sauvegarde ce DTO en faisant appel au service et on récupère un ResponseDTO
        PokemonResponseDto pokemonResponseDto = pokemonService.save(pokemonCreateDto);

        // on renvoie dans le corps de la requête réponse le ResponseDTO nouvellement sauvegardé
        return ResponseEntity.status(HttpStatus.CREATED).body(pokemonResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonResponseDto> findById(@PathVariable("id") Long id) {
        try {
            PokemonResponseDto pokemonResponseDto = pokemonService.findById(id);
            return ResponseEntity.ok(pokemonResponseDto);
        } catch (IllegalArgumentException e) {
            LOGGER.info("PokemonRestController a leve une exception " + e + " pour argument illegal: " + id);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        this.pokemonService.deleteById(id);
    }

    @GetMapping("/tous")
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

    @GetMapping
    public ResponseEntity<Iterable<Pokemon>> findByNomContainingIgnoreCaseOrderByNomDesc(@RequestParam(required = false) String nom) {
        Iterable<Pokemon> pokemons = pokemonService.findByNomContainingIgnoreCaseOrderByNomDesc(nom);
        return ResponseEntity.ok(pokemons);
    }
}