package com.pokemon.company.pokemon_joute.rest.controller;

import com.pokemon.company.pokemon_joute.model.Attaque;
import com.pokemon.company.pokemon_joute.service.AttaqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/attaques")
public class AttaqueRestController {

    @Autowired
    private AttaqueService attaqueService;

    @PostMapping
    public Attaque save(@RequestBody Attaque attaque) {
        return attaqueService.save(attaque);
    }

    @GetMapping("/{id}")
    public Attaque findById(@PathVariable Long id) {
        return attaqueService.findById(id);
    }

    @GetMapping("/toutes")
    public Iterable<Attaque> findAll() {
        return attaqueService.findAll();
    }

    @GetMapping("/search")
    public List<Attaque> findByNom(@RequestParam String nom) {
        return attaqueService.findByNom(nom);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        attaqueService.delete(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        attaqueService.deleteAll();
    }
}