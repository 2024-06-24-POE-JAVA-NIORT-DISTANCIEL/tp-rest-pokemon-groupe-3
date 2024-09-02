package com.pokemon.company.pokemon_joute.rest.controller;

import com.pokemon.company.pokemon_joute.dto.EspeceCreateDto;
import com.pokemon.company.pokemon_joute.dto.EspeceResponseDto;
import com.pokemon.company.pokemon_joute.mapper.EspeceMapper;
import com.pokemon.company.pokemon_joute.model.Dresseur;
import com.pokemon.company.pokemon_joute.model.Espece;
import com.pokemon.company.pokemon_joute.service.EspeceService;
import com.pokemon.company.pokemon_joute.utils.LogDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/especes")
public class EspeceRestController {

    private final static Logger LOGGER = Logger.getLogger(String.valueOf(EspeceRestController.class));

    private LogDetails logDetails = new LogDetails();

    @Autowired
    private EspeceService especeService;

    @Autowired
    private EspeceMapper especeMapper;

    @PostMapping
    public ResponseEntity<EspeceResponseDto> save(@RequestBody EspeceCreateDto especeCreateDto) {
        // @RequestBody : on récupère le Json et Spring Data le convertit en DTO

        // on sauvegarde ce DTO en faisant appel au service et on récupère un ResponseDTO
        EspeceResponseDto especeResponseDto = especeService.save(especeCreateDto);

        // on renvoie dans le corps de la requête réponse le ResponseDTO nouvellement sauvegardé
        return ResponseEntity.status(HttpStatus.CREATED).body(especeResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspeceResponseDto> findById(@PathVariable("id") Long id) {
        try {
            EspeceResponseDto especeResponseDto = especeService.findById(id);
            return ResponseEntity.ok(especeResponseDto);
        } catch (IllegalArgumentException e) {
            LOGGER.info("EspeceRestController a leve une exception " + e + " pour argument illegal: " + id);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/toutes")
    public ResponseEntity<List<EspeceResponseDto>> findAll() {
        List<EspeceResponseDto> especeResponseDtos = especeService.findAll();

        if (especeResponseDtos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // ici, on remap les especesResponseDtos (une liste de especeResponseDto) en especes (une liste d'Especes)
        // pour pouver envoyer cette liste à logDetails et afficher les détails dans la console
        List<Espece> especes = especeResponseDtos.stream()
                .map(especeMapper::toEspece) // Utilise le mapper (du package mapper) pour convertir chaque DTO en Espece
                .collect(Collectors.toList());
        logDetails.list(especes);

        return ResponseEntity.ok(especeResponseDtos);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try {
            especeService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/toutes")
    public ResponseEntity<Void> deleteAll() {
        especeService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}