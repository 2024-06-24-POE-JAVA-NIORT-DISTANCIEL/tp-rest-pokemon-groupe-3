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
    public DresseurDto save(@RequestBody Dresseur dresseur) {
        DresseurDto savedDresseur = dresseurService.save(dresseur);
        System.out.println("Sauvegarde du dresseur : \n" + savedDresseur.toString());
        return savedDresseur;
    }

    /*
    public ResponseEntity<Dresseur> getDresseur(@PathVariable("id") Long id){
        Dresseur savedDresseur = dresseurService.findById(id);

        if (savedDresseur != null) {
            System.out.println("Récupération du dresseur : \n" + savedDresseur);
            return ResponseEntity.ok(savedDresseur);
        } else {
            System.out.println("Le dresseur n'a pas été trouvé");
            return ResponseEntity.notFound().build();
        }
     */

    @GetMapping("/{id}")
    public DresseurDto getDresseur(@PathVariable("id") Long id) {
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
    public ResponseEntity<Void> deleteDresseur(@PathVariable("id") Long id) {
        DresseurDto dresseur = dresseurService.findById(id);

        if (dresseur != null) {
            System.out.println("Effacement du dresseur : \n" + dresseur);
            dresseurService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            System.out.println("Le dresseur n'existe pas, suppression impossible");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/tous")
    public void deleteAllDresseurs() {
        dresseurService.deleteAll();
    }
}
