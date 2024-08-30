package com.pokemon.company.pokemon_joute.service;

import com.pokemon.company.pokemon_joute.dao.AttaqueDao;
import com.pokemon.company.pokemon_joute.model.Attaque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AttaqueService {

    private final static Logger LOGGER = Logger.getLogger(String.valueOf(Attaque.class));

    @Autowired
    private AttaqueDao attaqueDao;

    public Attaque save(Attaque attaque) {
        LOGGER.info("L'attaque est sauvegardé");
        return attaqueDao.save(attaque);
    }

    public Optional<Attaque> findById(Long id) {
        LOGGER.info("L'attaque est récupéré par son identifiant");
        return attaqueDao.findById(id);
    }

    public Iterable<Attaque> findAll() {
        LOGGER.info("Les attaques sont récupéré par leur identifiant");
        return attaqueDao.findAll();
    }

    public List<Attaque> findByNom(String nom) {
        LOGGER.info("L'attaque est récupéré par son nom");
        return attaqueDao.findByNom(nom);
    }

    public void delete(Long id) {
        LOGGER.info("L'attaque est supprimé par son identifiant");
        attaqueDao.deleteById(id);
    }
}