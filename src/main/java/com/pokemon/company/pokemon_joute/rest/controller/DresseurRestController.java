package com.pokemon.company.pokemon_joute.rest.controller;

import com.pokemon.company.pokemon_joute.dto.*;
import com.pokemon.company.pokemon_joute.mapper.DresseurMapper;
import com.pokemon.company.pokemon_joute.mapper.ObjetMapper;
import com.pokemon.company.pokemon_joute.model.Dresseur;
import com.pokemon.company.pokemon_joute.model.Objet;
import com.pokemon.company.pokemon_joute.service.DresseurService;
import com.pokemon.company.pokemon_joute.utils.LogDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dresseurs")
public class DresseurRestController {

    private final static Logger LOGGER = Logger.getLogger(String.valueOf(DresseurRestController.class));

    private LogDetails logDetails = new LogDetails();

    @Autowired
    private DresseurService dresseurService;

    @Autowired
    private DresseurMapper dresseurMapper;

    @PostMapping
    public ResponseEntity<DresseurResponseDto> save(@RequestBody DresseurCreateDto dresseurCreateDto) {
        // @RequestBody : on récupère le Json et Spring Data le convertit en DTO

        // on sauvegarde ce DTO en faisant appel au service et on récupère un ResponseDTO
        DresseurResponseDto dresseurResponseDto = dresseurService.save(dresseurCreateDto);

        // on renvoie dans le corps de la requête réponse le ResponseDTO nouvellement sauvegardé
        return ResponseEntity.status(HttpStatus.CREATED).body(dresseurResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DresseurResponseDto> findById(@PathVariable("id") Long id) {
        try {
            DresseurResponseDto dresseurResponseDto = dresseurService.findById(id);
            return ResponseEntity.ok(dresseurResponseDto);
        } catch (IllegalArgumentException e) {
            LOGGER.info("DresseurRestController a leve une exception " + e + " pour argument illegal: " + id);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tous")
    public ResponseEntity<List<DresseurResponseDto>> findAll() {
        List<DresseurResponseDto> dresseurResponseDtos = dresseurService.findAll();

        if (dresseurResponseDtos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // ici, on remap les dresseurResponseDtos (une liste de dresseurResponseDto) en dresseurs (une liste de Dresseurs)
        // pour pouver envoyer cette liste à logDetails et afficher les détails dans la console
        List<Dresseur> dresseurs = dresseurResponseDtos.stream()
                .map(dresseurMapper::toDresseur) // Utilise le mapper (du package mapper) pour convertir chaque DTO en Dresseur
                .collect(Collectors.toList());
        logDetails.list(dresseurs);

        return ResponseEntity.ok(dresseurResponseDtos);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        DresseurResponseDto dresseur = dresseurService.findById(id);

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
    public void deleteAll() {
        dresseurService.deleteAll();
    }
}