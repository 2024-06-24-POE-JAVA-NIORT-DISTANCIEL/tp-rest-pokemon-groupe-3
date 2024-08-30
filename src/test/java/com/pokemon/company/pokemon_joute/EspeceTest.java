package com.pokemon.company.pokemon_joute;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pokemon.company.pokemon_joute.dao.EspeceDao;
import com.pokemon.company.pokemon_joute.model.Espece;

@SpringBootTest
public class EspeceTest {
	
	@Autowired
	EspeceDao especeDao;
	
	
	@Test
	public void testDeleteEspece() {
		
		Espece espece = new Espece();
		espece.setNom("un nom");
		
		Espece savedEspece = especeDao.save(espece);
		Assertions.assertNotNull(savedEspece);
		
		especeDao.delete(savedEspece);

        Optional<Espece> fetchedEspece = especeDao.findById(savedEspece.getId());
        Assertions.assertFalse(fetchedEspece.isPresent());
        System.out.println(espece + " a été effacée ");
	
	}
	
	@Test
	public void testSaveEspece() {
		
		Espece espece = new Espece();
		espece.setNom("un nom");
		
		Espece saved = especeDao.save(espece);
		
		
		Assertions.assertNotNull(saved.getId());
		Assertions.assertEquals("un nom", espece.getNom());
		
	}
	
	

}
