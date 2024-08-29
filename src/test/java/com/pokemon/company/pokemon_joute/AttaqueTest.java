package com.pokemon.company.pokemon_joute;

import com.pokemon.company.pokemon_joute.dao.AttaqueDao;
import com.pokemon.company.pokemon_joute.model.Attaque;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class AttaqueTest {

    @Autowired
    private AttaqueDao attaqueDao;

    @Test
    public void testSaveAttaque() {
        Attaque attaque = new Attaque();
        attaque.setNom("Lance-Soleil");

        Attaque savedAttaque = attaqueDao.save(attaque);
        Assertions.assertNotNull(savedAttaque.getId());
        Assertions.assertEquals("Lance-Soleil", savedAttaque.getNom());

        Optional<Attaque> fetchedAttaque = attaqueDao.findById(savedAttaque.getId());
        Assertions.assertTrue(fetchedAttaque.isPresent());
        System.out.println(attaque);
    }

    @Test
    public void testFindByNom() {
        Attaque attaque = new Attaque();
        attaque.setNom("Charge");

        attaqueDao.save(attaque);
        List<Attaque> attaques = attaqueDao.findByNom("Charge");
        Assertions.assertFalse(attaques.isEmpty());
        Assertions.assertEquals("Charge", attaques.get(0).getNom());
        System.out.println(attaque);
    }

    @Test
    public void testDeleteAttaque() {
        Attaque attaque = new Attaque();
        attaque.setNom("Tranch'Herbe");

        Attaque savedAttaque = attaqueDao.save(attaque);
        attaqueDao.delete(savedAttaque);

        Optional<Attaque> fetchedAttaque = attaqueDao.findById(savedAttaque.getId());
        Assertions.assertFalse(fetchedAttaque.isPresent());
        System.out.println(attaque + " a été effacée ");
    }
}