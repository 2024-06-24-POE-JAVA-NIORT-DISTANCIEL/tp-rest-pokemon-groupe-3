package com.pokemon.company.pokemon_joute.service;

import com.pokemon.company.pokemon_joute.dto.*;
import com.pokemon.company.pokemon_joute.model.Dresseur;
import com.pokemon.company.pokemon_joute.repository.DresseurRepository;
import com.pokemon.company.pokemon_joute.utils.LogDetails;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class DresseurService {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(Dresseur.class));

    private LogDetails logDetails = new LogDetails();

    @Autowired
    DresseurRepository dresseurRepository;

    @Transactional
    public DresseurResponseDto save(DresseurCreateDto dresseurCreateDto) {
        // le Service passe par les DTO pour ne pas manipuler directement les entités

        // on crée une Entité pour pouvoir associer l'entité Dresseur créée précédemment
        // pour ça on la fabrique à l'aide du DTO
        Dresseur dresseur = new Dresseur(
                dresseurCreateDto.getPseudo(),
                dresseurCreateDto.getMotDePasse()
        );

        // on sauvegarde cette entité via le repository
        // qui se charge des opérations courantes (extends CrudRepository)
        Dresseur savedDresseur = dresseurRepository.save(dresseur);

        LOGGER.info("Le dresseur '" + dresseur.getPseudo() + "' est sauvegarde");
        logDetails.single(dresseur);

        // on renvoie le DTO Réponse de cette entité au RestController
        return toDresseurResponseDto(Optional.of(savedDresseur));
    }

    public DresseurResponseDto findById(Long id) {
        return findById(id, false);
    }

    public DresseurResponseDto findById(Long id, Boolean disableLog) {
        Dresseur dresseur = dresseurRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aucun dresseur trouvée avec l'identifiant: " + id));
        if (!disableLog) {
            LOGGER.info("Le dresseur '" + dresseur.getPseudo() + "' est recupere par son identifiant: " + id);
            logDetails.single(dresseur);
        }

        return toDresseurResponseDto(dresseurRepository.findById(id));
    }

    @Transactional
    public void deleteById(Long id) {
        LOGGER.info("\nLe dresseur '" + dresseurRepository.findById(id) + "' est supprime par son identifiant: " + id);
        dresseurRepository.deleteById(id);
    }

    public List<DresseurResponseDto> findAll() {
        List<Dresseur> dresseurs = dresseurRepository.findAll();
        LOGGER.info("Tous les dresseurs sont recuperes (" + dresseurs.size() + " dresseur(s))");

        return dresseurRepository.findAll().stream()
                .map(dresseur -> toDresseurResponseDto(Optional.of(dresseur)))
                .collect(Collectors.toList());    }

    public void deleteAll() {
        LOGGER.info("Tous les dresseurs sont supprimes");
        dresseurRepository.deleteAll();
    }

    private DresseurDto toDto(Optional<Dresseur> dresseur) {
        if (dresseur.isEmpty()) {
            return null;
        }
        DresseurDto dto = new DresseurDto();
        dto.setPseudo(dresseur.get().getPseudo());
        if (dto.getPseudo().isEmpty()) {
            return null;
        }
        dto.setPorteFeuille(dresseur.get().getPortefeuille());
        dto.setPokemons(dresseur.get().getPokemons());
        dto.setDresseurObjets(dresseur.get().getDresseurObjets());
        return dto;
    }

    private Dresseur toEntity(DresseurDto dresseurDto) {
        if (dresseurDto == null) {
            return null;
        }
        Dresseur entity = new Dresseur();
        entity.setPseudo(dresseurDto.getPseudo());
        entity.setPortefeuille(dresseurDto.getPorteFeuille());
        entity.setPokemons(dresseurDto.getPokemons());
        entity.setDresseurObjets(dresseurDto.getDresseurObjets());
        return entity;
    }

    private DresseurResponseDto toDresseurResponseDto(Optional<Dresseur> dresseur) {
        if (dresseur.isEmpty()) {
            return null;
        }
        DresseurResponseDto dto = new DresseurResponseDto();
        dto.setId(dresseur.get().getId());
        dto.setPseudo(dresseur.get().getPseudo());
        dto.setPortefeuille(dresseur.get().getPortefeuille());
        return dto;
    }
}