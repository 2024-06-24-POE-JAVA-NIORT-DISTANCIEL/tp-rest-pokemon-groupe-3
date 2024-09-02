package com.pokemon.company.pokemon_joute.config;

import com.pokemon.company.pokemon_joute.rest.controller.PokemonJouteInit;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class StartupListener implements ApplicationListener<ApplicationReadyEvent> {
    private final static Logger LOGGER = Logger.getLogger(StartupListener.class.getName());
    private final PokemonJouteInit pokemonJouteInit;

    public StartupListener(PokemonJouteInit pokemonJouteInit) {
        this.pokemonJouteInit = pokemonJouteInit;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        LOGGER.info("èèèèèèèèèèèèèèèèèèèèèèèèèèè");
        LOGGER.info("èèèèèèèèèèèèèèèèèèèèèèèèèèè");
        LOGGER.info("èèèèèèèèèèèèèèèèèèèèèèèèèèè");

        LOGGER.info("\nLe serveur Tomcat est demarre et pret a ecouter.");
        LOGGER.info("\n" + pokemonJouteInit.start());
    }
}