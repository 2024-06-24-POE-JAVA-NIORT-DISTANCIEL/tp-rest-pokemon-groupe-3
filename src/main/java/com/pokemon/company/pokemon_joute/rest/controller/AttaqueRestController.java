package com.pokemon.company.pokemon_joute.rest.controller;

import com.pokemon.company.pokemon_joute.dto.AttaqueCreateDto;
import com.pokemon.company.pokemon_joute.dto.AttaqueResponseDto;
import com.pokemon.company.pokemon_joute.mapper.AttaqueMapper;
import com.pokemon.company.pokemon_joute.model.Attaque;
import com.pokemon.company.pokemon_joute.service.AttaqueService;
import com.pokemon.company.pokemon_joute.utils.LogDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/attaques")
public class AttaqueRestController {

    private final static Logger LOGGER = Logger.getLogger(String.valueOf(AttaqueRestController.class));

    private LogDetails logDetails = new LogDetails();

    @Autowired
    private AttaqueService attaqueService;

    @Autowired
    private AttaqueMapper attaqueMapper;

    @PostMapping
    public ResponseEntity<AttaqueResponseDto> save(@RequestBody AttaqueCreateDto attaqueCreateDto) {
        // @RequestBody : on récupère le Json et Spring Data le convertit en DTO

        // on sauvegarde ce DTO en faisant appel au service et on récupère un ResponseDTO
        AttaqueResponseDto attaqueResponseDto = attaqueService.save(attaqueCreateDto);

        // on renvoie dans le corps de la requête réponse le ResponseDTO nouvellement sauvegardé
        return ResponseEntity.status(HttpStatus.CREATED).body(attaqueResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttaqueResponseDto> findById(@PathVariable("id") Long id) {
        try {
            AttaqueResponseDto attaqueResponseDto = attaqueService.findById(id);
            return ResponseEntity.ok(attaqueResponseDto);
        } catch (IllegalArgumentException e) {
            LOGGER.info("AttaqueRestController a leve une exception " + e + " pour argument illegal: " + id);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/toutes")
    public ResponseEntity<List<AttaqueResponseDto>> findAll() {
        List<AttaqueResponseDto> attaqueResponseDtos = attaqueService.findAll();

        if (attaqueResponseDtos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // ici, on remap les attaqueResponseDtos (une liste de attaqueResponseDto) en attaques (une liste d'Attaque)
        // pour pouver envoyer cette liste à logDetails et afficher les détails dans la console
        List<Attaque> attaques = attaqueResponseDtos.stream()
                .map(attaqueMapper::toAttaque) // Utilise le mapper (du package mapper) pour convertir chaque DTO en Attaque
                .collect(Collectors.toList());
        logDetails.list(attaques);

        return ResponseEntity.ok(attaqueResponseDtos);
    }

    @GetMapping("/search")
    public List<Attaque> findByNom(@RequestParam String nom) {
        return attaqueService.findByNom(nom);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        attaqueService.delete(id);
    }

    @DeleteMapping("/toutes")
    public void deleteAll() {
        attaqueService.deleteAll();
    }
}