package com.pokemon.company.pokemon_joute.rest.controller;

import com.pokemon.company.pokemon_joute.dto.*;
import com.pokemon.company.pokemon_joute.service.*;
import com.pokemon.company.pokemon_joute.utils.Type;
import com.pokemon.company.pokemon_joute.utils.TypeObjet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.util.logging.Logger;

@Component
public class PokemonJouteInit {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(AttaqueService.class));
    private final AttaqueService attaqueService;
    private final ObjetService objetService;
    private final EspeceService especeService;
    private final PokemonService pokemonService;
    private final DresseurService dresseurService;
    @Autowired
    private DataSource dataSource;

    // ici on injecte le service dans le constructeur pour rendre attaqueService final
    // et rendre le code plus robuste (bonne pratique)
    @Autowired
    public PokemonJouteInit(
            AttaqueService attaqueService,
            ObjetService objetService,
            EspeceService especeService,
            PokemonService pokemonService,
            DresseurService dresseurService) {
        this.attaqueService = attaqueService;
        this.objetService = objetService;
        this.especeService = especeService;
        this.pokemonService = pokemonService;
        this.dresseurService = dresseurService;
    }

    public String start() {
        try {
            // on vérifie si les tables sont vides et s'il faut initialiser le projet
            if (verifTablesVides()) {

                // on lance le script SQL qui réinitialise la base de données (et vide tout)
                runSqlScript("sql/RESET-TOUT.sql");
                LOGGER.info("Toutes les tables sont vides, initialisation de la base de donnees...");

                initBDD();
            } else {
                LOGGER.info("Les tables ne sont pas vides, initialisation ignoree.");
            }
        } catch (Exception e) {
            LOGGER.severe("Erreur lors de l'initialisation de la base de donnees : " + e.getMessage());
        }

        return "Pokemon Joute est pret !";
    }

    private void runSqlScript(String scriptPath) throws Exception {
        File file = new File(scriptPath);
        String sql = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            LOGGER.info("Script SQL execute avec succes : " + scriptPath);
        }
    }

    private boolean verifTablesVides() {
        return attaqueService.findAll().isEmpty()
                && objetService.findAll().isEmpty()
                && especeService.findAll().isEmpty()
                && pokemonService.findAll().isEmpty()
                && dresseurService.findAll().isEmpty();
    }

    /**
     * Initialise le projet en envoyant à la base de données
     * ses premières entrées :
     * 5 attaques (1 par type : EAU, VOL, PLANTE, INSECTE, FEU)
     * 6 objets (2 par type : POKEBALL, POTION, DIVERS)
     * 15 espèces (3 par type : EAU, VOL, PLANTE, INSECTE, FEU)
     * 5 pokemons (1 par type : EAU, VOL, PLANTE, INSECTE, FEU)
     * 4 dresseurs
     */
    private void initBDD() {
        LOGGER.info("");
        LOGGER.info("*******************************");
        LOGGER.info("* Initialisation des attaques *");
        LOGGER.info("*******************************");
        LOGGER.info("");

        attaqueService.save(new AttaqueCreateDto("Big Splash", Type.EAU, 100));
        attaqueService.save(new AttaqueCreateDto("Vent divin", Type.VOL, 200));
        attaqueService.save(new AttaqueCreateDto("Pollens toxiques", Type.PLANTE, 300));
        attaqueService.save(new AttaqueCreateDto("Bzzzzzz ravageur", Type.INSECTE, 400));
        attaqueService.save(new AttaqueCreateDto("Flammes de l'enfer", Type.FEU, 500));

        LOGGER.info("");
        LOGGER.info("*****************************");
        LOGGER.info("* Initialisation des objets *");
        LOGGER.info("*****************************");
        LOGGER.info("");

        objetService.save(new ObjetCreateDto("Pokiballe", 40, TypeObjet.POKEBALL));
        objetService.save(new ObjetCreateDto("Pokeballon", 60, TypeObjet.POKEBALL));
        objetService.save(new ObjetCreateDto("Potion de souffle mortel", 450, TypeObjet.POTION));
        objetService.save(new ObjetCreateDto("Potion de soin aleatoire", 80, TypeObjet.POTION));
        objetService.save(new ObjetCreateDto("Sac a Pokemons", 210, TypeObjet.DIVERS));
        objetService.save(new ObjetCreateDto("Housse de couette", 550, TypeObjet.DIVERS));

        LOGGER.info("");
        LOGGER.info("******************************");
        LOGGER.info("* Initialisation des especes *");
        LOGGER.info("******************************");
        LOGGER.info("");

        especeService.save(new EspeceCreateDto("Carapuce", Type.EAU, 250, 1L));
        especeService.save(new EspeceCreateDto("Magicarpe", Type.EAU, 350, 1L));
        especeService.save(new EspeceCreateDto("Akwakwak", Type.EAU, 425, 1L));
        especeService.save(new EspeceCreateDto("Nosferapti", Type.VOL, 120, 2L));
        especeService.save(new EspeceCreateDto("Doduo", Type.VOL, 600, 2L));
        especeService.save(new EspeceCreateDto("Chovsourir", Type.VOL, 350, 2L));
        especeService.save(new EspeceCreateDto("Poussacha", Type.PLANTE, 440, 3L));
        especeService.save(new EspeceCreateDto("Marisson", Type.PLANTE, 180, 3L));
        especeService.save(new EspeceCreateDto("Majaspic", Type.PLANTE, 200, 3L));
        especeService.save(new EspeceCreateDto("Aspicot", Type.INSECTE, 100, 4L));
        especeService.save(new EspeceCreateDto("Parasect", Type.INSECTE, 800, 4L));
        especeService.save(new EspeceCreateDto("Aeromite", Type.INSECTE, 500, 4L));
        especeService.save(new EspeceCreateDto("Reptincel", Type.FEU, 400, 5L));
        especeService.save(new EspeceCreateDto("Pyroli", Type.FEU, 666, 5L));
        especeService.save(new EspeceCreateDto("Yuyu", Type.FEU, 135, 5L));

        LOGGER.info("");
        LOGGER.info("*******************************");
        LOGGER.info("* Initialisation des pokemons *");
        LOGGER.info("*******************************");
        LOGGER.info("");

        pokemonService.save(new PokemonCreateDto("Carpette", 2L));
        pokemonService.save(new PokemonCreateDto("Gros Dodu", 5L));
        pokemonService.save(new PokemonCreateDto("Marie", 8L));
        pokemonService.save(new PokemonCreateDto("As du Tricot", 10L));
        pokemonService.save(new PokemonCreateDto("Youpi", 15L));

        LOGGER.info("");
        LOGGER.info("********************************");
        LOGGER.info("* Initialisation des dresseurs *");
        LOGGER.info("********************************");
        LOGGER.info("");

        dresseurService.save(new DresseurCreateDto("LuluDu54", "1234"));
        dresseurService.save(new DresseurCreateDto("PetitPrince", "1234"));
        dresseurService.save(new DresseurCreateDto("LisaSimpson", "1234"));
        dresseurService.save(new DresseurCreateDto("ClaquettesChaussettes", "1234"));
    }
}
