package com.pokemon.company.pokemon_joute.service;

import com.pokemon.company.pokemon_joute.dao.AttaqueDao;
import com.pokemon.company.pokemon_joute.dto.AttaqueCreateDto;
import com.pokemon.company.pokemon_joute.dto.AttaqueResponseDto;
import com.pokemon.company.pokemon_joute.dto.PokemonResponseDto;
import com.pokemon.company.pokemon_joute.model.Attaque;
import com.pokemon.company.pokemon_joute.repository.AttaqueRepository;

import com.pokemon.company.pokemon_joute.utils.LogDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class AttaqueService {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(AttaqueService.class));

    private LogDetails logDetails = new LogDetails();

    @Autowired
    private AttaqueDao attaqueDao;

    @Autowired
    private AttaqueRepository attaqueRepository;

    @Transactional
    public AttaqueResponseDto save(AttaqueCreateDto attaqueCreateDto) {
        // le Service passe par les DTO pour ne pas manipuler directement les entités

        // on crée une Entité pour pouvoir associer l'entité Attaque créée précédemment
        // pour ça on la fabrique à l'aide du DTO
        Attaque attaque = new Attaque(
                attaqueCreateDto.getNom(),
                attaqueCreateDto.getType(),
                attaqueCreateDto.getDegats()
                );

        // on sauvegarde cette entité via le repository
        // qui se charge des opérations courantes (extends CrudRepository)
        Attaque savedAttaque = attaqueRepository.save(attaque);

        LOGGER.info("L'attaque '" + attaque.getNom() + "' est sauvegardee");
        logDetails.single(attaque);

        // on renvoie le DTO Réponse de cette entité au RestController
        return toAttaqueResponseDto(Optional.of(savedAttaque));
    }

    public AttaqueResponseDto findById(Long id) {
        return findById(id, false);
    }

    public AttaqueResponseDto findById(Long id, Boolean disableLog) {
        Attaque attaque = attaqueRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aucune attaque trouvée avec l'identifiant: " + id));
        if (!disableLog) {
            LOGGER.info("L'attaque '" + attaque.getNom() + "' est trouvee par son identifiant: " + id);
            logDetails.single(attaque);
        }

        return toAttaqueResponseDto(attaqueRepository.findById(id));
    }

    public List<AttaqueResponseDto> findAll() {
        List<Attaque> attaques = attaqueRepository.findAll();
        LOGGER.info("Toutes les attaques sont recuperees (" + attaques.size() + " attaque(s))");

        return attaqueRepository.findAll().stream()
                .map(attaque -> toAttaqueResponseDto(Optional.of(attaque)))
                .collect(Collectors.toList());
    }

    public List<Attaque> findByNom(String nom) {
        LOGGER.info("L'attaque '" + nom + "'est recuperee par son nom");
        List<Attaque> attaques = attaqueDao.findByNom(nom);
        logDetails.list(attaques);

        return attaques;
    }

    public void delete(Long id) {
        LOGGER.info("L'attaque '" + attaqueRepository.findById(id) + "' est supprimee par son identifiant: " + id);
        attaqueRepository.deleteById(id);
    }

    public void deleteAll() {
        attaqueRepository.deleteAll();
    }

    private AttaqueResponseDto toAttaqueResponseDto(Optional<Attaque> attaque) {
        if (attaque.isEmpty()) {
            return null;
        }
        AttaqueResponseDto dto = new AttaqueResponseDto();
        dto.setId(attaque.get().getId());
        dto.setNom(attaque.get().getNom());
        dto.setType(attaque.get().getType());
        dto.setDegats(attaque.get().getDegats());
        return dto;
    }

    public List<Attaque> findByIds(List<Long> attaqueIds) {
        return attaqueRepository.findAllById(attaqueIds);
    }
}