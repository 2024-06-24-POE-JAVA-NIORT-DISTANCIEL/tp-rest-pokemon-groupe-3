package com.pokemon.company.pokemon_joute.rest.controller;

import com.pokemon.company.pokemon_joute.dto.PokemonDto;
import com.pokemon.company.pokemon_joute.model.Pokemon;
import com.pokemon.company.pokemon_joute.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pokemons")
public class PokemonRestController {

    @Autowired
    PokemonService pokemonService;

    @GetMapping("/{id}")
    public ResponseEntity<PokemonDto> findById(@PathVariable("id") Long id) {
        PokemonDto pokemonDto = pokemonService.findById(id);
        if (pokemonDto == null) {
            return ResponseEntity.ok(pokemonDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<PokemonDto> save(@RequestBody Pokemon pokemon) {
        PokemonDto savedPokemonDto = pokemonService.save(pokemon);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPokemonDto);
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
