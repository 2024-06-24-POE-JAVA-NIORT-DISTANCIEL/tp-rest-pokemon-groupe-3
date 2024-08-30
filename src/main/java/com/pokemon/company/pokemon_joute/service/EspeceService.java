package com.pokemon.company.pokemon_joute.service;

import com.pokemon.company.pokemon_joute.dao.EspeceDao;
import com.pokemon.company.pokemon_joute.model.Espece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class EspeceService {

	private final static Logger LOGGER = Logger.getLogger(String.valueOf(Espece.class));
	
	@Autowired
	EspeceDao especeDao;
	
	public Optional<Espece> findById(Long especeId) {
		LOGGER.info("L'espèce du pokémon est récupéré par son identifiant");
		return this.especeDao.findById(especeId);
	}

	public Espece save(Espece espece) {
		LOGGER.info("L'espèce du pokémon est sauvegardé par son identifiant");
		return especeDao.save(espece);
	}

	public void deleteById(Long id) {
		LOGGER.info("L'espèce du pokémon est supprimé par son identifiant");
		this.especeDao.deleteById(id);
	}



	
}
