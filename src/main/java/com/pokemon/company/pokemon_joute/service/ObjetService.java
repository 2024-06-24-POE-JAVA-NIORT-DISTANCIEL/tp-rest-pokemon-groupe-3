package com.pokemon.company.pokemon_joute.service;

import com.pokemon.company.pokemon_joute.model.Objet;
import com.pokemon.company.pokemon_joute.repository.ObjetRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObjetService {

    @Autowired
    ObjetRepository objetRepository;

    public Objet save(Objet objet){
        Objet savedObjet = this.objetRepository.save(objet);
        return savedObjet;
    }

    public Objet findById(Long id){
        Optional<Objet> optionalObjet = this.objetRepository.findById(id);
        if (optionalObjet.isEmpty()){
            return null;
        } else {
            return optionalObjet.get();
        }
    }

    @Transactional
    public void deleteById(Long id) {
        objetRepository.deleteById(id);
    }

    public List<Objet> findAll(){
        return this.objetRepository.findAll();
    }

    public void deleteAll() {
        objetRepository.deleteAll();
    }

}
