package com.pokemon.company.pokemon_joute.service;

import com.pokemon.company.pokemon_joute.dto.ObjetCreateDto;
import com.pokemon.company.pokemon_joute.dto.ObjetResponseDto;
import com.pokemon.company.pokemon_joute.model.Dresseur;
import com.pokemon.company.pokemon_joute.model.DresseurObjet;
import com.pokemon.company.pokemon_joute.model.Objet;
import com.pokemon.company.pokemon_joute.repository.DresseurRepository;
import com.pokemon.company.pokemon_joute.repository.ObjetRepository;
import com.pokemon.company.pokemon_joute.utils.LogDetails;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class ObjetService {

    private final static Logger LOGGER = Logger.getLogger(String.valueOf(Objet.class));

    private LogDetails logDetails = new LogDetails();

    @Autowired
    ObjetRepository objetRepository;

    @Autowired
    DresseurRepository dresseurRepository;

    @Transactional
    public ObjetResponseDto save(ObjetCreateDto objetCreateDto) {
        // le Service passe par les DTO pour ne pas manipuler directement les entités

        // on crée une Entité pour pouvoir associer l'entité Objet créée précédemment
        // pour ça on la fabrique à l'aide du DTO
        Objet objet = new Objet(
                objetCreateDto.getNom(),
                objetCreateDto.getPrix(),
                objetCreateDto.getTypeObjet()
        );

        // on sauvegarde cette entité via le repository
        // qui se charge des opérations courantes (extends CrudRepository)
        Objet savedObjet = objetRepository.save(objet);

        LOGGER.info("L'objet '" + objet.getNom() + "' est sauvegarde");
        logDetails.single(objet);

        // on renvoie le DTO Réponse de cette entité au RestController
        return toObjetResponseDto(Optional.of(savedObjet));
    }

    public ObjetResponseDto findById(Long id) {
        return findById(id, false);
    }

    public ObjetResponseDto findById(Long id, Boolean disableLog) {
        Objet objet = objetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aucun objet trouve avec l'identifiant: " + id));
        if (!disableLog) {
            LOGGER.info("L'objet '" + objet.getNom() + "' est trouve par son identifiant: " + id);
            logDetails.single(objet);
        }

        return toObjetResponseDto(objetRepository.findById(id));
    }

    @Transactional
    public void deleteById(Long id) {
        Objet objet = objetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aucun objet trouve avec l'identifiant: " + id + "\n"));

        LOGGER.info("L'objet '" + objet.getNom() + "' est supprime par son identifiant: " + id);
        logDetails.single(objet);
        objetRepository.deleteById(id);
    }

    public List<ObjetResponseDto> findAll() {
        List<Objet> objets = objetRepository.findAll();
        LOGGER.info("Tous les objets sont recuperes (" + objets.size() + " objet(s))");

        return objetRepository.findAll().stream()
                .map(objet -> toObjetResponseDto(Optional.of(objet)))
                .collect(Collectors.toList());
    }

    public void deleteAll() {
        LOGGER.info("Tous les objets sont supprimes ");
        objetRepository.deleteAll();
    }

    @Transactional
    public String acheter(Long dresseurId, Long objetId) {
        Optional<Dresseur> optionalDresseur = dresseurRepository.findById(dresseurId);
        Optional<Objet> optionalObjet = objetRepository.findById(objetId);

        if (optionalDresseur.isEmpty() || optionalObjet.isEmpty()) {
            return "\nDresseur ou objet non trouve";
        }

        Dresseur dresseur = optionalDresseur.get();
        Objet objet = optionalObjet.get();

        if (dresseur.getPortefeuille() < objet.getPrix()) {
            return "\nTu n'as pas assez d'argent, snif :(";
        }

        if (dresseur.getDresseurObjets() != null) {
            boolean foundInInventaire = false;
            for (DresseurObjet dresseurObjet : dresseur.getDresseurObjets()) {
                if (dresseurObjet.getObjet().getId().equals(objet.getId())) {
                    dresseurObjet.setQuantite(dresseurObjet.getQuantite() + 1);
                    foundInInventaire = true;
                    break;
                }
            }
            if (!foundInInventaire) {
                DresseurObjet nouveauDresseurObjet = new DresseurObjet(dresseur, objet, 1);
                dresseur.getDresseurObjets().add(nouveauDresseurObjet);
            }
        }

        dresseur.setPortefeuille(dresseur.getPortefeuille() - objet.getPrix());
        dresseurRepository.save(dresseur);

        return "Bravo ! Tu as bien achete '" + objet.getNom() + "'";
    }

    public List<Objet> findByPlageDePrix(Integer prixMin, Integer prixMax) {
        LOGGER.info("\nfindByPlageDePrix : requete de recuperation d'objets (parametree)");

        // on déclare une variable pour stocker nos objets mais celle-ci ne sera initialisée qu'après
        // si et seulement si des objets existent et sont trouvés par le objetRepository !
        // ici, objets est null par défaut
        List<Objet> objets;

        if (prixMin == null && prixMax == null) {
            objets = objetRepository.findAll();
            if (!objets.isEmpty()) LOGGER.info(objets.size() + " objets trouves :");
        } else if (prixMin == null) {
            objets = objetRepository.findByPrixLessThanEqual(prixMax);
            if (!objets.isEmpty()) LOGGER.info(objets.size() + " objets trouves coutant moins que " + prixMax + " Pokedollars :");
        } else if (prixMax == null) {
            objets = objetRepository.findByPrixGreaterThanEqual(prixMin);
            if (!objets.isEmpty()) LOGGER.info(objets.size() + " objets trouves coutant plus que " + prixMin + " Pokedollars :");
        } else {
            objets = objetRepository.findByPrixBetween(prixMin, prixMax);
            if (!objets.isEmpty()) LOGGER.info(objets.size() + " objets trouves entre " + prixMin + " et " + prixMax + " Pokedollars :");
        }

        // ici on peut faire isEmpty() car objets n'est plus null, c'est une liste vide
        // que Spring Data a gentiment faite pour nous
        if (objets.isEmpty()) {
            LOGGER.info("Aucun objet trouve dans la plage de prix");
        } else {
            logDetails.list(objets);
        }

        return objets;
    }

    private ObjetResponseDto toObjetResponseDto(Optional<Objet> objet) {
        if (objet.isEmpty()) {
            return null;
        }
        ObjetResponseDto dto = new ObjetResponseDto();
        dto.setId(objet.get().getId());
        dto.setNom(objet.get().getNom());
        dto.setPrix(objet.get().getPrix());
        dto.setTypeObjet(objet.get().getTypeObjet());
        return dto;
    }
}