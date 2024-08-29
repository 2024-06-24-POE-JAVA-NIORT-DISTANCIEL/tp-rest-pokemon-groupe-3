package com.pokemon.company.pokemon_joute.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokemon.company.pokemon_joute.dao.EspeceDao;
import com.pokemon.company.pokemon_joute.model.Espece;

@Service
public class EspeceService {
	
	@Autowired
	EspeceDao especeDao;
	
	public Optional<Espece> findById(Long especeId) {
		return this.especeDao.findById(especeId);
	}
	
	public Espece save(Espece espece) {		
		return especeDao.save(espece);
	}
	
	public void deleteById(Long id) {
		this.especeDao.deleteById(id);
	}



	
}
