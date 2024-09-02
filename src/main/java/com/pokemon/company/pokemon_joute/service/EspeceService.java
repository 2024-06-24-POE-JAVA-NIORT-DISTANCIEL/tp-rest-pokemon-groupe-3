package com.pokemon.company.pokemon_joute.service;

import com.pokemon.company.pokemon_joute.dao.EspeceDao;
import com.pokemon.company.pokemon_joute.dto.EspeceCreateDto;
import com.pokemon.company.pokemon_joute.dto.EspeceResponseDto;
import com.pokemon.company.pokemon_joute.model.Attaque;
import com.pokemon.company.pokemon_joute.model.Espece;
import com.pokemon.company.pokemon_joute.repository.AttaqueRepository;
import com.pokemon.company.pokemon_joute.repository.EspeceRepository;
import com.pokemon.company.pokemon_joute.repository.PokemonRepository;
import com.pokemon.company.pokemon_joute.utils.LogDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class EspeceService {

    private final static Logger LOGGER = Logger.getLogger(String.valueOf(EspeceService.class));

    private LogDetails logDetails = new LogDetails();

    @Autowired
    EspeceDao especeDao;

    @Autowired
    private EspeceRepository especeRepository;

    @Autowired
    private AttaqueRepository attaqueRepository;

    @Autowired
    private PokemonRepository pokemonRepository;

    @Transactional
    public EspeceResponseDto save(EspeceCreateDto especeCreateDto) {
        // le Service passe par les DTO pour ne pas manipuler directement les entités

        // on récupère l'id fournie par le DTO (donc venant du JSON)
        Long attaqueInitialeId = especeCreateDto.getAttaqueInitialeId();

        // on utilise le Repository pour accéder à la base Postgres
        // pour une opération courante comme findById
        // et on vérifie si l'attaque existe
        Attaque attaqueInitiale = attaqueRepository.findById(attaqueInitialeId)
                .orElseThrow(() -> new IllegalArgumentException("Aucune attaque ne possede l'id: " + attaqueInitialeId));

        // on crée une Entité pour pouvoir associer l'entité Attaque créée précédemment
        // pour ça on la fabrique à l'aide du DTO
        Espece espece = new Espece(
                especeCreateDto.getNom(),
                especeCreateDto.getType(),
                especeCreateDto.getPvInitial(),
                attaqueInitiale);

        // on réalise l'association proprement dite
        espece.setAttaqueInitiale(attaqueInitiale);

        // on sauvegarde cette entité via le repository
        // qui se charge des opérations courantes (extends CrudRepository)
        Espece savedEspece = especeRepository.save(espece);

        LOGGER.info("L'espece '" + espece.getNom() + "' est sauvegardee");
        logDetails.single(espece);

        // on renvoie le DTO Réponse de cette entité au RestController
        return toEspeceResponseDto(Optional.of(savedEspece));
    }

    public EspeceResponseDto findById(Long id) {
        return findById(id, false);
    }

    public EspeceResponseDto findById(Long id, Boolean disableLog) {
        Espece espece = especeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aucune espece trouvée avec l'identifiant: " + id));
        if (!disableLog) {
            LOGGER.info("L'espece '" + espece.getNom() + "' est recuperee par son identifiant: " + id);
            logDetails.single(espece);
        }
        return toEspeceResponseDto(especeRepository.findById(id));
    }

    public List<EspeceResponseDto> findAll() {
        List<Espece> especes = especeRepository.findAll();
        LOGGER.info("Toutes les especes sont recuperees (" + especes.size() + " espece(s))");

        return especeRepository.findAll().stream()
                .map(espece -> toEspeceResponseDto(Optional.of(espece)))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteById(Long id) {
        // on vérifie s'il y a un pokemon de cette espèce
        if (pokemonRepository.existsByEspeceId(id)) {
            String msgErreur = "Impossible de supprimer l'espece car elle est associee a au moins un Pokemon";
            LOGGER.info(msgErreur);
            throw new IllegalStateException(msgErreur);
        }
        LOGGER.info("L'espece '" + especeRepository.findById(id).get().getNom() + "' est supprimee par son identifiant: " + id);
        especeRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll() {
        LOGGER.info("Toutes les especes sont supprimees");
        especeRepository.deleteAll();
    }

    private EspeceResponseDto toEspeceResponseDto(Optional<Espece> espece) {
        if (espece.isEmpty()) {
            return null;
        }
        EspeceResponseDto dto = new EspeceResponseDto();
        dto.setId(espece.get().getId());
        dto.setNom(espece.get().getNom());
        dto.setType(espece.get().getType());
        dto.setPvInitial(espece.get().getPvInitial());
        dto.setAttaqueInitialeId(espece.get().getAttaqueInitiale().getId());
        return dto;
    }
}