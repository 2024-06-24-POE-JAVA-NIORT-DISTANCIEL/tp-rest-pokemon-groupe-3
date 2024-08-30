package com.pokemon.company.pokemon_joute.service;

import com.pokemon.company.pokemon_joute.dto.DresseurDto;
import com.pokemon.company.pokemon_joute.model.Dresseur;
import com.pokemon.company.pokemon_joute.repository.DresseurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class DresseurService {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(Dresseur.class));

    @Autowired
    DresseurRepository dresseurRepository;

    @Transactional
    public DresseurDto save(Dresseur dresseur) {
        LOGGER.info("Le dresseur est sauvegardé");
        return toDto(Optional.of(dresseurRepository.save(dresseur)));
    }

    public DresseurDto findById(Long id) {
        LOGGER.info("Le dresseur est récupéré par son identifiant");
        return toDto(this.dresseurRepository.findById(id));
    }

    @Transactional
    public void deleteById(Long id) {
        LOGGER.info("Le dresseur est supprimé par son identifiant");
        dresseurRepository.deleteById(id);
    }

    public List<Dresseur> findAll() {
        LOGGER.info("Les dresseur sont tous affichés");
        return this.dresseurRepository.findAll();
    }

    public void deleteAll() {
        LOGGER.info("Les dresseur sont tous supprimés");
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
        dto.setInventaires(dresseur.get().getInventaires());
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
        entity.setInventaires(dresseurDto.getInventaires());
        return entity;
    }

}
