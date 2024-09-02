package com.pokemon.company.pokemon_joute.mapper;

import com.pokemon.company.pokemon_joute.dto.EspeceResponseDto;
import com.pokemon.company.pokemon_joute.model.Attaque;
import com.pokemon.company.pokemon_joute.model.Espece;
import com.pokemon.company.pokemon_joute.service.AttaqueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EspeceMapper {

    @Autowired
    private AttaqueService attaqueService;

    @Autowired
    private AttaqueMapper attaqueMapper;

    public EspeceResponseDto toEspeceResponseDto(Espece espece) {
        if (espece == null) {
            return null;
        }
        EspeceResponseDto dto = new EspeceResponseDto();
        dto.setId(espece.getId());
        dto.setNom(espece.getNom());
        dto.setType(espece.getType());
        dto.setPvInitial(espece.getPvInitial());
        dto.setAttaqueInitialeId(espece.getAttaqueInitiale().getId());

        return dto;
    }

    public Espece toEspece(EspeceResponseDto dto) {
        if (dto == null) {
            return null;
        }
        Espece espece = new Espece();
        espece.setId(dto.getId());
        espece.setNom(dto.getNom());
        espece.setType(dto.getType());
        espece.setPvInitial(dto.getPvInitial());
        Attaque attaqueInitiale = attaqueMapper.toAttaque(attaqueService.findById(dto.getAttaqueInitialeId(), true));
        espece.setAttaqueInitiale(attaqueInitiale);

        return espece;
    }
}
