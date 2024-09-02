package com.pokemon.company.pokemon_joute;

import com.pokemon.company.pokemon_joute.model.Objet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class PokemonJouteApplication {
    private final static Logger LOGGER = Logger.getLogger(String.valueOf(Objet.class));

    public static void main(String[] args) {
        LOGGER.info("èèèèèèèèèè");
        LOGGER.info("èèèèèèèèèè");
        LOGGER.info("èèèèèèèèèè");
        LOGGER.info("èèèèèèèèèè");
        LOGGER.info("èèèèèèèèèè");
        SpringApplication.run(PokemonJouteApplication.class, args);
    }
}