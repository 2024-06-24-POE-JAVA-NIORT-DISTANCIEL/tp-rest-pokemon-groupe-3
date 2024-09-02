package com.pokemon.company.pokemon_joute;

import com.pokemon.company.pokemon_joute.dao.AttaqueDao;
import com.pokemon.company.pokemon_joute.model.Attaque;
import com.pokemon.company.pokemon_joute.repository.AttaqueRepository;
import com.pokemon.company.pokemon_joute.utils.Type;
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

    @Autowired
    private AttaqueRepository attaqueRepository;

    @Test
    public void testSaveAttaque() {
        Attaque attaque = new Attaque();
        attaque.setNom("Lance-Soleil");
        attaque.setType(Type.PLANTE);

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
        attaque.setType(Type.VOL);

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
        attaque.setType(Type.PLANTE);

        Attaque savedAttaque = attaqueDao.save(attaque);
        attaqueDao.delete(savedAttaque);

        Optional<Attaque> fetchedAttaque = attaqueDao.findById(savedAttaque.getId());
        Assertions.assertFalse(fetchedAttaque.isPresent());
        System.out.println(attaque + " a été effacée ");
    }
}