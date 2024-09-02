package com.pokemon.company.pokemon_joute.mapper;

import com.pokemon.company.pokemon_joute.dto.PokemonResponseDto;
import com.pokemon.company.pokemon_joute.model.Attaque;
import com.pokemon.company.pokemon_joute.model.Espece;
import com.pokemon.company.pokemon_joute.model.Pokemon;
import com.pokemon.company.pokemon_joute.service.AttaqueService;
import com.pokemon.company.pokemon_joute.service.EspeceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PokemonMapper {

    @Autowired
    private EspeceService especeService;

    @Autowired
    private AttaqueService attaqueService;

    @Autowired
    private EspeceMapper especeMapper;

    public PokemonResponseDto toPokemonResponseDto(Pokemon pokemon) {
        if (pokemon == null) {
            return null;
        }
        PokemonResponseDto dto = new PokemonResponseDto();
        dto.setId(pokemon.getId());
        dto.setNom(pokemon.getNom());
        dto.setNiveau(pokemon.getNiveau());
        dto.setExperience(pokemon.getExperience());
        dto.setPv(pokemon.getPv());
        dto.setPvMax(pokemon.getPvMax());
        dto.setEspeceId(pokemon.getEspece().getId());

        List<Long> attaqueIds = pokemon.getAttaques().stream()
                .map(Attaque::getId)
                .collect(Collectors.toList());
        dto.setAttaqueIds(attaqueIds);
        return dto;
    }

    public Pokemon toPokemon(PokemonResponseDto dto) {
        if (dto == null) {
            return null;
        }
        Pokemon pokemon = new Pokemon();
        pokemon.setId(dto.getId());
        pokemon.setNom(dto.getNom());
        pokemon.setNiveau(dto.getNiveau());
        pokemon.setExperience(dto.getExperience());
        pokemon.setPv(dto.getPv());
        pokemon.setPvMax(dto.getPvMax());
        Espece espece = especeMapper.toEspece(especeService.findById(dto.getEspeceId(), true));
        pokemon.setEspece(espece);
        List<Attaque> attaques = attaqueService.findByIds(dto.getAttaqueIds());
        pokemon.setAttaques(attaques);
        return pokemon;
    }
}
