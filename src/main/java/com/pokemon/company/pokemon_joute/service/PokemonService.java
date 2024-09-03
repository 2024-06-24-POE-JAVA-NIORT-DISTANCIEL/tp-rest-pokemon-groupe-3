package com.pokemon.company.pokemon_joute.service;

import com.pokemon.company.pokemon_joute.dao.*;
import com.pokemon.company.pokemon_joute.dto.*;
import com.pokemon.company.pokemon_joute.exception.PokemonCombatException;
import com.pokemon.company.pokemon_joute.mapper.AttaqueMapper;
import com.pokemon.company.pokemon_joute.mapper.PokemonMapper;
import com.pokemon.company.pokemon_joute.model.*;
import com.pokemon.company.pokemon_joute.repository.*;
import com.pokemon.company.pokemon_joute.utils.LogDetails;
import com.pokemon.company.pokemon_joute.utils.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    //attributs

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(Pokemon.class));

    @Autowired
    PokemonDao pokemonDao;

    @Autowired
    PokemonRepository pokemonRepository;

    @Autowired
    EspeceDao especeDao;

    @Autowired
    EspeceRepository especeRepository;

    @Autowired
    EspeceService especeService;

    @Autowired
    AttaqueService attaqueService;

    @Autowired
    AttaqueRepository attaqueRepository;

    @Autowired
    AttaqueMapper attaqueMapper;

    private LogDetails logDetails = new LogDetails();

    @Autowired
    private PokemonMapper pokemonMapper;

    private Scanner kb = new Scanner(System.in);

    // méthodes publiques

    @Transactional
    public PokemonResponseDto save(PokemonCreateDto pokemonCreateDto) {
        // le Service passe par les DTO pour ne pas manipuler directement les entités

        // on récupère l'id fournie par le DTO (donc venant du JSON)
        Long especeId = pokemonCreateDto.getEspeceId();

        // on utilise le Repository pour accéder à la base Postgres
        // pour une opération courante comme findById
        // et on vérifie si l'espèce existe
        Espece espece = especeRepository.findById(especeId)
                .orElseThrow(() -> new IllegalArgumentException("Aucune espece ne possede l'id: " + especeId));

        // on crée une Entité pour pouvoir associer l'entité Espece créée précédemment
        // pour ça on la fabrique à l'aide du DTO
        Pokemon pokemon = new Pokemon(pokemonCreateDto.getNom(), espece);

        // on réalise l'association proprement dite
        pokemon.setEspece(espece);

        // on sauvegarde cette entité via le repository
        // qui se charge des opérations courantes (extends CrudRepository)
        Pokemon savedPokemon = pokemonRepository.save(pokemon);

        LOGGER.info("Le pokemon '" + pokemon.getNom() + "' est sauvegarde");
        logDetails.single(pokemon);

        // on renvoie le DTO Réponse de cette entité au RestController
        return toPokemonResponseDto(Optional.of(savedPokemon));
    }

    @Transactional
    public PokemonResponseDto updatePokemonApresCombat(PokemonResponseDto pokemonResponseDto) {
        // on récupère l'id du pokemon depuis le DTO
        Long pokemonId = pokemonResponseDto.getId();

        // on va chercher le pokemon en base à l'aide du repository par son id
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new IllegalArgumentException("Aucun Pokemon ne possede l'id: " + pokemonId));

        // on met à jour le pokemon avec les nouvelles valeurs du DTO
        pokemon.setNiveau(pokemonResponseDto.getNiveau());
        pokemon.setExperience(pokemonResponseDto.getExperience());
        pokemon.setPv(pokemonResponseDto.getPvMax());
        pokemon.setPvMax(pokemonResponseDto.getPvMax());

        // récupération des objets Attaque correspondants aux IDs des attaques
        List<Long> attaqueIds = pokemonResponseDto.getAttaqueIds();
        List<Attaque> attaques = attaqueRepository.findAllById(attaqueIds);

        // mise à jour des attaques du Pokémon
        pokemon.setAttaques(attaques);

        // on sauvegarde en base à l'aide du repository
        Pokemon updatedPokemon = pokemonRepository.save(pokemon);

        LOGGER.info(updatedPokemon.getNom() + " est mis a jour apres le combat");
        logDetails.single(updatedPokemon);
        kb.nextLine();

        // on convertit le pokemon en DTO réponse avant de le renvoyer
        return toPokemonResponseDto(Optional.of(updatedPokemon));
    }

    public PokemonResponseDto findById(Long id) {
        return findById(id, false);
    }

    public PokemonResponseDto findById(Long id, Boolean disableLog) {
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aucun pokemon trouvé avec l'identifiant: " + id));
        if (!disableLog) {
            LOGGER.info("Le pokemon '" + pokemon.getNom() + "' est trouve par son identifiant: " + id);
            logDetails.single(pokemon);
        }

        return toPokemonResponseDto(pokemonRepository.findById(id));
    }

    public void deleteById(Long id) {
        try {
            pokemonDao.deleteById(id);
            LOGGER.info("Le pokemon '" + pokemonRepository.findById(id) + "' a ete supprime par son identifiant");
        } catch (EmptyResultDataAccessException e) {
            System.err.println("Erreur : Aucun pokemon trouve avec l'ID " + id);
        }
    }

    public List<PokemonResponseDto> findAll() {
        List<Pokemon> pokemons = pokemonRepository.findAll();
        LOGGER.info("Tous les pokemons sont recuperes (" + pokemons.size() + " pokemon(s))");

        return pokemonRepository.findAll().stream()
                .map(pokemon -> toPokemonResponseDto(Optional.of(pokemon)))
                .collect(Collectors.toList());
    }

    public Iterable<Pokemon> findByNomContainingIgnoreCaseOrderByNomDesc(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            LOGGER.info("Le nom du pokemon n'est pas dans la base de donnee");
            return this.pokemonRepository.findAll();
        }
        LOGGER.info("La liste des pokemons par nom dans l'ordre descendant");
        return pokemonDao.findByNomContainingIgnoreCaseOrderByNomDesc(nom);
    }

    private PokemonDto toDto(Optional<Pokemon> pokemon) {
        if (pokemon.isEmpty()) {
            return null;
        }
        PokemonDto dto = new PokemonDto();
        dto.setNom(pokemon.get().getNom());
        dto.setNiveau(pokemon.get().getNiveau());
        dto.setPv(pokemon.get().getPv());
        dto.setPvMax(pokemon.get().getPvMax());
        dto.setExperience(pokemon.get().getExperience());
        dto.setEspeceId(pokemon.get().getEspece().getId());
        return dto;
    }

    private Pokemon toEntity(PokemonDto pokemonDto) {
        if (pokemonDto == null) {
            return null;
        }
        Pokemon entity = new Pokemon();
        entity.setNom(pokemonDto.getNom());
        entity.setNiveau(pokemonDto.getNiveau());
        entity.setPv(pokemonDto.getPv());
        entity.setPvMax(pokemonDto.getPvMax());
        entity.setExperience(pokemonDto.getExperience());
        Optional<Espece> espece = especeDao.findById(pokemonDto.getEspeceId());
        if (espece.isEmpty()) {
            return null;
        }
        entity.setEspece(espece.get());
        return entity;
    }

    public PokemonResponseDto toPokemonResponseDto(Optional<Pokemon> pokemon) {
        if (pokemon.isEmpty()) {
            return null;
        }
        PokemonResponseDto dto = new PokemonResponseDto();
        dto.setId(pokemon.get().getId());
        dto.setNom(pokemon.get().getNom());
        dto.setNiveau(pokemon.get().getNiveau());
        dto.setExperience(pokemon.get().getExperience());
        dto.setPv(pokemon.get().getPv());
        dto.setPvMax(pokemon.get().getPvMax());
        dto.setEspeceId(pokemon.get().getEspece().getId());

        // on récupère la Liste d'objets Attaques du Pokemon et on les place dans un flux (stream)
        dto.setAttaqueIds(pokemon.get().getAttaques().stream()

                // pour chacune d'entre elle, on récupère l'id et on remap le flux avec une référence de méthode
                .map(Attaque::getId)
                // on remet tout ça dans une Liste pour remplir la liste d'id d'attaques du dto réponse
                .collect(Collectors.toList()));

        return dto;
    }

    public void combatsDePokemon(Long id1, Long id2) {

        // pour rendre la lecture du code aisée, on nomme nos Dtos
        // combattantA et combattantB, plutôt que combattantAResponseDto et combattantBResponseDto
        // (sans ambiguïté, dans un service, on manipule des DTO)

        // on initialise les combattants en les retrouvant par leur id
        PokemonResponseDto combattantA = findById(id1, true);
        PokemonResponseDto combattantB = findById(id2, true);

        int largeurTitre = 53; // Nombre total de caractères pour la ligne titre
        String nomCombattantA = combattantA.getNom();
        String nomCombattantB = combattantB.getNom();
        String sousTitre = nomCombattantA + " affronte " + nomCombattantB;
        int largeurSousTitre = sousTitre.length();
        String padding = " ".repeat((largeurTitre - largeurSousTitre) / 2 - 1);
        String ligne2 = "=" + padding + sousTitre + padding + "=";
        LOGGER.info("=".repeat(largeurTitre));
        LOGGER.info("=                  DEBUT DU COMBAT                  =");
        LOGGER.info(ligne2);
        LOGGER.info("=".repeat(largeurTitre));
        kb.nextLine();

        if (combattantA.getPv() == 0 || combattantB.getPv() == 0) {
            // On lance une exception personnalisée si le combat ne peut se faire
            throw new PokemonCombatException("On ne peut pas faire combattre un pokemon qui a 0 point de vie !");
        }

        // les points de vie des pokemons sont remis au maximum pour le combat
        combattantA.setPv(combattantA.getPvMax());
        combattantB.setPv(combattantB.getPvMax());

        // on tire un booléen aléatoire, on garde A et B tels qu'on les a initialisé s'il est false,
        // sinon on inverse les combattants
        if (new Random().nextBoolean()) {
            combattantA = findById(id2, true);
            combattantB = findById(id1, true);
        }

        LOGGER.info("Que le combat de Pokemons commence !\n");
        kb.nextLine();

        // le combat se déroule tant qu'aucun combattant n'a ses points de vie à 0
        // la sortie de boucle est gérée par le break et assurée par le code de effectuerTourDeCombat
        // qui renverra true au bout d'un certains nombre d'appels
        int tourDeCombat = 1;
        while (true) {
            LOGGER.info("-----------------------------------------------------");
            LOGGER.info("              Tour de combat " + tourDeCombat);
            LOGGER.info("----------------------------------------------------- \n");
            LOGGER.info(combattantA.getNom() + " attaque !");
            if (effectuerTourDeCombat(combattantA, combattantB)) break;
            LOGGER.info(".".repeat(30));
            kb.nextLine();
            LOGGER.info(combattantB.getNom() + " replique !");
            if (effectuerTourDeCombat(combattantB, combattantA)) break;
            tourDeCombat++;
            kb.nextLine();
        }
    }

    // méthodes privées

    // ************* METHODES ASSOCIEES AU COMBAT *************

    private AttaqueResponseDto choisirAttaqueAleatoire(PokemonResponseDto pokemon) {
        List<Long> listeIds = pokemon.getAttaqueIds();

        LOGGER.info("Liste des attaques de " + pokemon.getNom() + " :");
        for (Long id : listeIds) {
            AttaqueResponseDto attaque = attaqueService.findById(id, true);
            LOGGER.info("- " + attaque.getNom() + " (ID: " + attaque.getId() + ")");
        }

        Random random = new Random();
        int randomIndex = random.nextInt(listeIds.size()); // Génère un index aléatoire
        Long randomId = listeIds.get(randomIndex); // Récupère l'id de l'attaque tirée au hasard
        return attaqueService.findById(randomId, true);
    }

    private boolean effectuerTourDeCombat(PokemonResponseDto attaquant, PokemonResponseDto defenseur) {
        AttaqueResponseDto attaqueAleatoire = choisirAttaqueAleatoire(attaquant);
        LOGGER.info(attaquant.getNom() + " utilise " + attaqueAleatoire.getNom() + " sur " + defenseur.getNom());

        defenseur = executerAttaque(attaquant.getNiveau(), attaqueMapper.toAttaque(attaqueAleatoire), defenseur);

        // le combat est-il terminé ?
        if (defenseur.getPv() == 0) {
            kb.nextLine();
            finDeCombat(attaquant, defenseur);
            return true;
        }

        // non, on continue !
        return false;
    }

    private PokemonResponseDto executerAttaque(int niveauAttaquant, Attaque attaque, PokemonResponseDto defenseur) {

        double nbPvPerdu;

        double[][] modificateursAttaque = new double[5][5];

        // 0 = vol
        modificateursAttaque[0][0] = 1;
        modificateursAttaque[0][1] = 0.7;
        modificateursAttaque[0][2] = 1;
        modificateursAttaque[0][3] = 1.3;
        modificateursAttaque[0][4] = 1;

        // 1 = eau
        modificateursAttaque[1][0] = 1.3;
        modificateursAttaque[1][1] = 1;
        modificateursAttaque[1][2] = 0.7;
        modificateursAttaque[1][3] = 1;
        modificateursAttaque[1][4] = 1.3;

        // 2 = insecte
        modificateursAttaque[2][0] = 1;
        modificateursAttaque[2][1] = 1.3;
        modificateursAttaque[2][2] = 1;
        modificateursAttaque[2][3] = 0.7;
        modificateursAttaque[2][4] = 0.7;

        // 3 = plante
        modificateursAttaque[3][0] = 0.7;
        modificateursAttaque[3][1] = 1;
        modificateursAttaque[3][2] = 1.3;
        modificateursAttaque[3][3] = 1;
        modificateursAttaque[3][4] = 0.7;

        // 4 = feu
        modificateursAttaque[4][0] = 1;
        modificateursAttaque[4][1] = 0.7;
        modificateursAttaque[4][2] = 1.3;
        modificateursAttaque[4][3] = 1.3;
        modificateursAttaque[4][4] = 0.7;

        EspeceResponseDto espece = especeService.findById(defenseur.getEspeceId(), true);
        Type typeEspece = espece.getType();
        Type typeAttaque = attaque.getType();

        int indexTypeEspece, indexTypeAttaque;
        String string1, string2;

        switch (typeAttaque) {
            case VOL -> {
                indexTypeAttaque = 0;
                string1 = "[Attaque VOL sur ";
            }
            case EAU -> {
                indexTypeAttaque = 1;
                string1 = "[Attaque EAU sur ";
            }
            case INSECTE -> {
                indexTypeAttaque = 2;
                string1 = "[Attaque INSECTE sur ";
            }
            case PLANTE -> {
                indexTypeAttaque = 3;
                string1 = "[Attaque PLANTE sur ";
            }
            case FEU -> {
                indexTypeAttaque = 4;
                string1 = "[Attaque FEU sur ";
            }
            default -> {
                indexTypeAttaque = 1_000; // garantit une valeur, laisse les index 5+ pour un ajout de types
                string1 = "[Attaque par défaut sur ";
            }
        }

        switch (typeEspece) {
            case VOL -> {
                indexTypeEspece = 0;
                string2 = "Pokemon VOL]";
            }
            case EAU -> {
                indexTypeEspece = 1;
                string2 = "Pokemon EAU]";
            }
            case INSECTE -> {
                indexTypeEspece = 2;
                string2 = "Pokemon INSECTE]";
            }
            case PLANTE -> {
                indexTypeEspece = 3;
                string2 = "Pokemon VOL]";
            }
            case FEU -> {
                indexTypeEspece = 4;
                string2 = "Pokemon FEU]";
            }
            default -> {
                indexTypeEspece = 1_000; // idem
                string2 = "Pokemon par défaut]";
            }
        }

        LOGGER.info(string1 + string2);

        double modificateurAttaque = modificateursAttaque[indexTypeAttaque][indexTypeEspece];
        LOGGER.info("Modificateur d'attaque -> " + modificateurAttaque);
        nbPvPerdu = ((double) niveauAttaquant / 10) * attaque.getDegats() * modificateurAttaque;
        LOGGER.info(defenseur.getNom() + " perd " + (int) nbPvPerdu + " point(s) de vie");

        if (modificateurAttaque < 1) {
            LOGGER.info("Ce n'est pas tres efficace ...");
        }

        if (modificateurAttaque > 1) {
            LOGGER.info("C'est super efficace !");
        }

        double nouveauPv = defenseur.getPv() - nbPvPerdu;
        if (nouveauPv < 0) {
            nouveauPv = 0;
        }
        LOGGER.info(defenseur.getNom() + " a " + (int) nouveauPv + " point(s) de vie restant(s)\n");
        defenseur.setPv((int) (nouveauPv));

        return defenseur;
    }

    private void finDeCombat(PokemonResponseDto pokemonGagnant, PokemonResponseDto pokemonPerdant) {

        int experienceGagnee = pokemonPerdant.getNiveau() * 4;

        LOGGER.info(pokemonPerdant.getNom() + " est K.O. Il ne peut plus combattre...");

        LOGGER.info(pokemonGagnant.getNom() + " est le gagnant !\n");
        kb.nextLine();

        int ancienXp = pokemonGagnant.getExperience();
        int xpRestante = ancienXp % 5;
        int surplusXp = xpRestante + experienceGagnee;
        int niveauxGagnes = surplusXp / 5;

        // on met à jour l'expérience du pokémon après une victoire
        LOGGER.info(pokemonGagnant.getNom() + " gagne " + experienceGagnee + " point(s) d'experience");
        pokemonGagnant.setExperience(pokemonGagnant.getExperience() + experienceGagnee);

        // on met à jour les pvMax
        int nouveauxPvMax = pokemonGagnant.getPvMax() + 10 * niveauxGagnes;
        LOGGER.info(pokemonGagnant.getNom() + " gagne " + 10 * niveauxGagnes + " point(s) de vie maximum !\n");
        pokemonGagnant.setPvMax(nouveauxPvMax);

        // on met à jour les niveaux
        LOGGER.info("*** " + niveauxGagnes + " niveau(x) gagne(s) ***");
        pokemonGagnant.setNiveau(pokemonGagnant.getNiveau() + niveauxGagnes);

        // on récupère les attaques déjà possédées par le Pokémon et les attaques en base
        List<Long> attaqueIdsExistantes = pokemonGagnant.getAttaqueIds();
        List<AttaqueResponseDto> toutesLesAttaques = attaqueService.findAll(true);

        // on filtre pour trouver celles que le pokemon n'a pas
        List<AttaqueResponseDto> attaquesDisponibles = toutesLesAttaques.stream()
                .filter(attaqueDto -> !attaqueIdsExistantes.contains(attaqueDto.getId()))
                .collect(Collectors.toList());

        if (attaquesDisponibles.isEmpty()) {
            LOGGER.info(pokemonGagnant.getNom() + " possede deja toutes les attaques disponibles !");
        } else {
            Random random = new Random();
            for (int i = 0; i < niveauxGagnes; i++) {

                // on choisit une attaque aléatoire parmi les attaques disponibles
                AttaqueResponseDto attaqueGagnee = attaquesDisponibles.get(random.nextInt(attaquesDisponibles.size()));

                // on ajoute l'attaque à la liste des attaques du Pokémon
                attaqueIdsExistantes.add(attaqueGagnee.getId());

                // on met à jour les attaques du Pokémon proprement dites
                pokemonGagnant.setAttaqueIds(attaqueIdsExistantes);

                LOGGER.info(pokemonGagnant.getNom() + " a gagne une nouvelle attaque : " + attaqueGagnee.getNom());
                kb.nextLine();

                // on retire l'attaque gagnee des attaques disponibles
                attaquesDisponibles.remove(attaqueGagnee);

                // on verifie si toutes les attaques disponibles ont été gagnées avant de continuer la boucle
                if (attaquesDisponibles.isEmpty()) {
                    LOGGER.info(pokemonGagnant.getNom() + " a maintenant appris toutes les attaques disponibles !");
                    break;
                }
            }
        }

        updatePokemonApresCombat(pokemonGagnant);

        LOGGER.info("=============       COMBAT TERMINE       =============\n");
    }

    // *******************************************************

}