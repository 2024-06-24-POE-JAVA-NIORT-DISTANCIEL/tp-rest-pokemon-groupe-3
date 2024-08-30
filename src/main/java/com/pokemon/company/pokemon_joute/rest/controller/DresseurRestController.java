package com.pokemon.company.pokemon_joute.rest.controller;

import com.pokemon.company.pokemon_joute.dto.DresseurDto;
import com.pokemon.company.pokemon_joute.model.Dresseur;
import com.pokemon.company.pokemon_joute.service.DresseurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dresseurs")
public class DresseurRestController {
    @Autowired
    private DresseurService dresseurService;

    @PostMapping
    public DresseurDto save(@RequestBody Dresseur dresseur){
        DresseurDto savedDresseur = dresseurService.save(dresseur);
        System.out.println("Sauvegarde du dresseur : \n" + savedDresseur.toString());
        return savedDresseur;
    }

    @GetMapping("/{id}")
    public DresseurDto getDresseur(@PathVariable("id") Long id){
        DresseurDto savedDresseur = dresseurService.findById(id);
        System.out.println("Récupération du dresseur : \n" + savedDresseur.toString());
        return savedDresseur;
    }

    @GetMapping("/tous")
    public ResponseEntity<List<Dresseur>> getAllDresseurs() {
        List<Dresseur> dresseurs = dresseurService.findAll();
        System.out.println("Nombre de dresseurs: " + dresseurs.size());

        return ResponseEntity.ok(dresseurs);
    }

    @DeleteMapping("{id}")
    public void deleteDresseur(@PathVariable("id") Long id){
        System.out.println("Effacement du dresseur : \n" + dresseurService.findById(id).toString());
        dresseurService.deleteById(id);
    }

    @DeleteMapping("/tous")
    public void deleteAllDresseurs() {
        dresseurService.deleteAll();
    }
}
