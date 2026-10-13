package com.ensak.venteanalytics.mapper;

import com.ensak.venteanalytics.dto.HistoriqueRechercheDTO;
import com.ensak.venteanalytics.entity.HistoriqueRecherche;
import com.ensak.venteanalytics.entity.Acheteur;

public class HistoriqueRechercheMapper {

    public static HistoriqueRechercheDTO toDTO(HistoriqueRecherche entity) {
        return HistoriqueRechercheDTO.builder()
                .id(entity.getId())
                .termeRecherche(entity.getTermeRecherche())
                .dateRecherche(entity.getDateRecherche())
                .acheteurId(entity.getAcheteur() != null ? entity.getAcheteur().getId() : null)
                .build();
    }

    public static HistoriqueRecherche toEntity(HistoriqueRechercheDTO dto, Acheteur acheteur) {
        HistoriqueRecherche entity = new HistoriqueRecherche();
        entity.setId(dto.getId());
        entity.setTermeRecherche(dto.getTermeRecherche());
        entity.setDateRecherche(dto.getDateRecherche());
        entity.setAcheteur(acheteur);
        return entity;
    }
}
