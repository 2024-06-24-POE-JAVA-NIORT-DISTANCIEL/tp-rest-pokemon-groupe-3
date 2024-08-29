package com.pokemon.company.pokemon_joute.service;

import com.pokemon.company.pokemon_joute.model.Dresseur;
import com.pokemon.company.pokemon_joute.repository.DresseurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DresseurService {

    @Autowired
    DresseurRepository dresseurRepository;

    public Dresseur save(Dresseur dresseur){
        Dresseur savedDresseur = this.dresseurRepository.save(dresseur);
        return savedDresseur;
    }

    public Dresseur findById(Long id){
        Optional<Dresseur> optionalDresseur = this.dresseurRepository.findById(id);
        if (optionalDresseur.isEmpty()){
            return null;
        } else {
            return optionalDresseur.get();
        }
    }

    @Transactional
    public void deleteById(Long id) {
        dresseurRepository.deleteById(id);
    }

    public List<Dresseur> findAll(){
        return this.dresseurRepository.findAll();
    }

    public void deleteAll() {
        dresseurRepository.deleteAll();
    }

}
