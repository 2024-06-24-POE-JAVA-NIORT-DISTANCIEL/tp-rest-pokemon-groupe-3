package com.pokemon.company.pokemon_joute.mapper;

import com.pokemon.company.pokemon_joute.dto.DresseurResponseDto;
import com.pokemon.company.pokemon_joute.model.Dresseur;
import com.pokemon.company.pokemon_joute.model.Objet;
import org.springframework.stereotype.Component;

@Component
public class DresseurMapper {

    public DresseurResponseDto toDresseurResponseDto(Dresseur dresseur) {
        if (dresseur == null) {
            return null;
        }
        DresseurResponseDto dto = new DresseurResponseDto();
        dto.setId(dresseur.getId());
        dto.setPseudo(dresseur.getPseudo());
        dto.setPortefeuille(dresseur.getPortefeuille());
        return dto;
    }

    public Dresseur toDresseur(DresseurResponseDto dto) {
        if (dto == null) {
            return null;
        }
        Dresseur dresseur = new Dresseur();
        dresseur.setId(dto.getId());
        dresseur.setPseudo(dto.getPseudo());
        dresseur.setPortefeuille(dto.getPortefeuille());
        return dresseur;
    }
}
