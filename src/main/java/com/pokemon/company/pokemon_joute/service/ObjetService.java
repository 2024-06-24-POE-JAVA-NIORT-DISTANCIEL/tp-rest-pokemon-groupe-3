package com.pokemon.company.pokemon_joute.service;

import com.pokemon.company.pokemon_joute.model.Objet;
import com.pokemon.company.pokemon_joute.repository.ObjetRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ObjetService {

    private final static Logger LOGGER = Logger.getLogger(String.valueOf(Objet.class));


    @Autowired
    ObjetRepository objetRepository;

    public Objet save(Objet objet) {
        LOGGER.info("L'objet est sauvegardé");
        return this.objetRepository.save(objet);
    }

    public Objet findById(Long id) {
        Optional<Objet> optionalObjet = this.objetRepository.findById(id);
        if (optionalObjet.isEmpty()) {
            return null;
        } else {
            LOGGER.info("L'objet est trouvé par son identifiant");
            return optionalObjet.get();
        }
    }

    @Transactional
    public void deleteById(Long id) {
        LOGGER.info("L'objet est supprimé par son identifiant");
        objetRepository.deleteById(id);
    }

    public List<Objet> findAll() {
        LOGGER.info("Les objets son tous affichés");
        return this.objetRepository.findAll();
    }

    public void deleteAll() {
        LOGGER.info("Tous les objets sont supprimés");
        objetRepository.deleteAll();
    }

}
