package com.pokemon.company.pokemon_joute.rest.controller;

import com.pokemon.company.pokemon_joute.dto.ObjetCreateDto;
import com.pokemon.company.pokemon_joute.dto.ObjetResponseDto;
import com.pokemon.company.pokemon_joute.mapper.ObjetMapper;
import com.pokemon.company.pokemon_joute.model.Objet;
import com.pokemon.company.pokemon_joute.service.ObjetService;

import com.pokemon.company.pokemon_joute.utils.LogDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
public class ObjetRestController {

    private final static Logger LOGGER = Logger.getLogger(String.valueOf(ObjetRestController.class));

    private LogDetails logDetails = new LogDetails();

    @Autowired
    private ObjetService objetService;

    @Autowired
    private ObjetMapper objetMapper;

    @PostMapping("/objets")
    public ResponseEntity<ObjetResponseDto> save(@RequestBody ObjetCreateDto objetCreateDto) {
        // @RequestBody : on récupère le Json et Spring Data le convertit en DTO

        // on sauvegarde ce DTO en faisant appel au service et on récupère un ResponseDTO
        ObjetResponseDto objetResponseDto = objetService.save(objetCreateDto);

        // on renvoie dans le corps de la requête réponse le ResponseDTO nouvellement sauvegardé
        return ResponseEntity.status(HttpStatus.CREATED).body(objetResponseDto);
    }

    @GetMapping("/objets/{id}")
    public ResponseEntity<ObjetResponseDto> findById(@PathVariable("id") Long id) {
        try {
            ObjetResponseDto objetResponseDto = objetService.findById(id);
            return ResponseEntity.ok(objetResponseDto);
        } catch (IllegalArgumentException e) {
            LOGGER.info("ObjetRestController a leve une exception " + e + " pour argument illegal: " + id);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/objets/tous")
    public ResponseEntity<List<ObjetResponseDto>> findAll() {
        List<ObjetResponseDto> objetResponseDtos = objetService.findAll();

        if (objetResponseDtos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // ici, on remap les objetResponseDtos (une liste de objetResponseDto) en objets (une liste d'Objet)
        // pour pouver envoyer cette liste à logDetails et afficher les détails dans la console
        List<Objet> objets = objetResponseDtos.stream()
                .map(objetMapper::toObjet) // Utilise le mapper (du package mapper) pour convertir chaque DTO en Objet
                .collect(Collectors.toList());
        logDetails.list(objets);

        return ResponseEntity.ok(objetResponseDtos);
    }

    @GetMapping("/objets")
    public ResponseEntity<List<Objet>> findByPlageDePrix(
            @RequestParam(value = "prixMin", required = false) Integer prixMin,
            @RequestParam(value = "prixMax", required = false) Integer prixMax) {

        List<Objet> objets = objetService.findByPlageDePrix(prixMin, prixMax);
        return ResponseEntity.ok(objets);
    }

    @PostMapping("/achats")
    public ResponseEntity<String> acheter(@RequestParam Long dresseurId, @RequestParam Long objetId) {
        String resultat = objetService.acheter(dresseurId, objetId);

        if ("transaction success".equals(resultat)) {
            LOGGER.info("La transaction s'est deroulee correctement\n");
            return ResponseEntity.ok(resultat);
        } else {
            LOGGER.info("La transaction n'a pas pu etre effectuee\n");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultat);
        }
    }

    @DeleteMapping("/objets/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        objetService.deleteById(id);
    }

    @DeleteMapping("/objets/tous")
    public void deleteAll() {
        objetService.deleteAll();
    }
}