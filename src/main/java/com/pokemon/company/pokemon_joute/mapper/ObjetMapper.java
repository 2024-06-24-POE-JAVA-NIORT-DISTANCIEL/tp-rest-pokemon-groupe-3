package com.pokemon.company.pokemon_joute.mapper;

import com.pokemon.company.pokemon_joute.dto.ObjetResponseDto;
import com.pokemon.company.pokemon_joute.model.Objet;
import org.springframework.stereotype.Component;

@Component
public class ObjetMapper {

    public ObjetResponseDto toObjetResponseDto(Objet objet) {
        if (objet == null) {
            return null;
        }
        ObjetResponseDto dto = new ObjetResponseDto();
        dto.setId(objet.getId());
        dto.setNom(objet.getNom());
        dto.setPrix(objet.getPrix());
        dto.setTypeObjet(objet.getTypeObjet());
        return dto;
    }

    public Objet toObjet(ObjetResponseDto dto) {
        if (dto == null) {
            return null;
        }
        Objet objet = new Objet();
        objet.setId(dto.getId());
        objet.setNom(dto.getNom());
        objet.setPrix(dto.getPrix());
        objet.setTypeObjet(dto.getTypeObjet());
        return objet;
    }
}
