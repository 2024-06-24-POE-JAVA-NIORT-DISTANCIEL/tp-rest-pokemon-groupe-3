package com.pokemon.company.pokemon_joute.service;

import com.pokemon.company.pokemon_joute.dao.AttaqueDao;
import com.pokemon.company.pokemon_joute.model.Attaque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttaqueService {

    @Autowired
    private AttaqueDao attaqueDao;

    public Attaque save(Attaque attaque) {
        return attaqueDao.save(attaque);
    }

    public Attaque findById(Long id) {
        Optional<Attaque> optionalAttaque = this.attaqueDao.findById(id);
        if (optionalAttaque.isEmpty()) {
            return null;
        }
        return optionalAttaque.get();
    }

    public Iterable<Attaque> findAll() {
        return attaqueDao.findAll();
    }

    public List<Attaque> findByNom(String nom) {
        return attaqueDao.findByNom(nom);
    }

    public void delete(Long id) {
        attaqueDao.deleteById(id);
    }

    public void deleteAll() {
        attaqueDao.deleteAll();
    }
}