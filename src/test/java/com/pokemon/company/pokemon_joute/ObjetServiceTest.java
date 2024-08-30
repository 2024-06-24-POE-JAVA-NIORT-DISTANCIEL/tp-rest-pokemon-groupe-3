package com.pokemon.company.pokemon_joute;

import com.pokemon.company.pokemon_joute.model.Dresseur;
import com.pokemon.company.pokemon_joute.model.Inventaire;
import com.pokemon.company.pokemon_joute.model.Objet;
import com.pokemon.company.pokemon_joute.repository.DresseurRepository;
import com.pokemon.company.pokemon_joute.repository.ObjetRepository;
import com.pokemon.company.pokemon_joute.service.ObjetService;
import com.pokemon.company.pokemon_joute.utils.TypeObjet;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ObjetServiceTest {

        @Autowired
        ObjetRepository objetRepository;

        @Autowired
        DresseurRepository dresseurRepository;

    @Autowired
    ObjetService objetService;

    @Test
    @Transactional
    public void testAcheterObjet_Success() {
        // Créer un dresseur avec suffisamment d'argent
        Dresseur dresseur = new Dresseur();
        dresseur.setMotDePasse("Imthedelegue");
        dresseur.setPseudo("Charles");
        dresseur.setPortefeuille(1500);
        dresseur.setInventaires(new ArrayList<>()); // Initialisation de la liste des inventaires
        dresseurRepository.save(dresseur);

        // Créer un objet avec un prix
        Objet objet = new Objet();
        objet.setNom("Superball");
        objet.setPrix(150);
        objet.setType(TypeObjet.POKEBALL);
        objet = objetRepository.save(objet); //objetRepository.save(objet);

        // Acheter l'objet
        String resultat = objetService.acheterObjet(dresseur.getId(), objet.getId());

        // Vérifications
        Assertions.assertEquals("Bravo ! Tu as bien acheté une Pokeball", resultat);
        Dresseur dresseurApresAchat = dresseurRepository.findById(dresseur.getId()).get();
        Assertions.assertEquals(1500 - 250, dresseurApresAchat.getPortefeuille()); // Vérifier que l'argent a été décrémenté

        Assertions.assertNotNull(dresseurApresAchat.getInventaires());
        Assertions.assertEquals(1, dresseurApresAchat.getInventaires().size()); // Vérifier que l'objet a été ajouté à l'inventaire
        Assertions.assertEquals(1, dresseurApresAchat.getInventaires().get(0).getQuantite()); // Vérifier la quantité
    }

    @Test
    @Transactional
    public void testAcheterObjet_NotEnoughMoney() {
        // Créer un dresseur avec manque d'argent
        Dresseur dresseur = new Dresseur();
        dresseur.setMotDePasse("Iamtheboss");
        dresseur.setPseudo("Dominique");
        dresseur.setPortefeuille(100);
        dresseur.setInventaires(new ArrayList<>()); // Initialisation de la liste des inventaires
        dresseurRepository.save(dresseur);

        // Créer un objet avec un prix élevé
        Objet objet = new Objet();
        objet.setNom("Velo");
        objet.setPrix(100000);
        objet.setType(TypeObjet.DIVERS);
        objetRepository.save(objet);

        // Essayer d'acheter l'objet
        String resultat = objetService.acheterObjet(dresseur.getId(), objet.getId());

        Assertions.assertEquals("Tu n'as pas assez d'argent, snif :(", resultat);
        Dresseur dresseurApresTentative = new Dresseur();
        dresseurApresTentative = dresseurRepository.findById(dresseur.getId()).get();
        // vérifier que c'est pas nul (.get)
        Assertions.assertEquals(100, dresseurApresTentative.getPortefeuille()); // Vérifier que l'argent n'a pas changé
        Assertions.assertTrue(dresseurApresTentative.getInventaires().isEmpty()); // Vérifier que l'inventaire est vide
    }

    @Test
    @Transactional
    public void testAcheterObjet_DresseurOrObjetNotFound() {
        // Essayer d'acheter un objet avec un dresseur ou un objet inexistant
        String resultat = objetService.acheterObjet(999L, 999L);

        Assertions.assertEquals("Dresseur ou objet non trouvé", resultat);
    }}