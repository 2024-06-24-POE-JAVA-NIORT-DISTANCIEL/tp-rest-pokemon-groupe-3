package com.pokemon.company.pokemon_joute.utils;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class LogDetails {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(LogDetails.class));

    public <T> void single(T object) {
        LOGGER.info(object.toString());
    }

    public <T> void list(List<T> objects) {
        LOGGER.info(objects.stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n")) + "\n");
    }
}
