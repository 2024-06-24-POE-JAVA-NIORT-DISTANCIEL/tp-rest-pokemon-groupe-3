package com.pokemon.company.pokemon_joute.rest.controller;

import com.pokemon.company.pokemon_joute.model.Pokemon;
import com.pokemon.company.pokemon_joute.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pokemons")
public class PokemonRestController {

    @Autowired
    PokemonService pokemonService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pokemon>> findById(@PathVariable("id") Long id) {
        Optional<Pokemon> pokemon = pokemonService.findById(id);
        if (pokemon.isPresent()) {
            return ResponseEntity.ok(pokemon);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Pokemon> save(@RequestBody Pokemon pokemon) {
        Pokemon savedPokemon = pokemonService.save(pokemon);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPokemon);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        this.pokemonService.deleteById(id);
    }

    @GetMapping("findAll")
    public ResponseEntity<Iterable<Pokemon>> findAll() {
        Iterable<Pokemon> pokemon = pokemonService.findAll();
        if (!pokemon.iterator().hasNext()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(pokemon);
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<Pokemon>> findByNomContainingIgnoreCaseOrderByNomDesc(@RequestParam(required = false) String nom) {
        Iterable<Pokemon> pokemons = pokemonService.findByNomContainingIgnoreCaseOrderByNomDesc(nom);
        return ResponseEntity.ok(pokemons);
    }

}
