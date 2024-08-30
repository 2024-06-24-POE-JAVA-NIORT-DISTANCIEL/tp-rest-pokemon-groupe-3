package com.pokemon.company.pokemon_joute.rest.controller;

import com.pokemon.company.pokemon_joute.model.Objet;
import com.pokemon.company.pokemon_joute.service.ObjetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/objets")
public class ObjetRestController {
    @Autowired
    private ObjetService objetService;

    @PostMapping
    public Objet save(@RequestBody Objet objet){
        System.out.println("Objet reçu : " + objet);
        Objet savedObjet = objetService.save(objet);
        return savedObjet;
    }
    @GetMapping("/{id}")
    public Objet getObjet(@PathVariable("id") Long id){
        Objet savedObjet = objetService.findById(id);
        return savedObjet;
    }

    @GetMapping("/tous")
    public ResponseEntity<List<Objet>> getAllObjets() {
        List<Objet> objets = objetService.findAll();
        System.out.println("Nombre d'objets: " + objets.size());

        return ResponseEntity.ok(objets);
    }

    @PostMapping("//achats")
        public ResponseEntity<String> acheterObjet(@RequestParam Long dresseurId, @RequestParam Long objetId) {
            String resultat = objetService.acheterObjet(dresseurId, objetId);
            if ("Bravo ! Tu as bien acheté l'objet".equals(resultat)) {
                return ResponseEntity.ok(resultat);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultat);
            }
        }

    @DeleteMapping("{id}")
    public void deleteObjet(@PathVariable("id") Long id){
        objetService.deleteById(id);
    }

    @DeleteMapping("/tous")
    public void deleteAllObjets() {
        objetService.deleteAll();
    }
}
