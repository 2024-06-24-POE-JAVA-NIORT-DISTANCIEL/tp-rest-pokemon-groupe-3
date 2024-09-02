package com.pokemon.company.pokemon_joute.mapper;

import com.pokemon.company.pokemon_joute.dto.AttaqueResponseDto;
import com.pokemon.company.pokemon_joute.model.Attaque;

import org.springframework.stereotype.Component;

@Component
public class AttaqueMapper {

    public AttaqueResponseDto toAttaqueResponseDto(Attaque attaque) {
        if (attaque == null) {
            return null;
        }
        AttaqueResponseDto dto = new AttaqueResponseDto();
        dto.setId(attaque.getId());
        dto.setNom(attaque.getNom());
        dto.setType(attaque.getType());
        dto.setDegats(attaque.getDegats());
        return dto;
    }

    public Attaque toAttaque(AttaqueResponseDto dto) {
        if (dto == null) {
            return null;
        }
        Attaque attaque = new Attaque();
        attaque.setId(dto.getId());
        attaque.setNom(dto.getNom());
        attaque.setType(dto.getType());
        attaque.setDegats(dto.getDegats());
        return attaque;
    }
}
