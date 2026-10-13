package com.ensak.venteanalytics.mapper;

import com.ensak.venteanalytics.dto.AlerteDTO;
import com.ensak.venteanalytics.entity.Alerte;
import com.ensak.venteanalytics.entity.Vendeur;

import java.time.LocalDate;

public class AlerteMapper {

    /**
     * DTO -> ENTITY
     * Le vendeur est injecté depuis le service (vendeur connecté)
     */
    public static Alerte toEntity(AlerteDTO dto, Vendeur vendeur) {

        Alerte alerte = new Alerte();

        alerte.setId(dto.getId());
        alerte.setTypeAlerte(dto.getTypeAlerte());
        alerte.setMessage(dto.getMessage());

        // Date obligatoire (fallback sécurité)
        alerte.setDateCreation(
                dto.getDateCreation() != null
                        ? dto.getDateCreation()
                        : LocalDate.now()
        );

        // 🔐 vendeur connecté
        alerte.setVendeur(vendeur);

        return alerte;
    }

    /**
     * ENTITY -> DTO
     */
    public static AlerteDTO toDTO(Alerte alerte) {

        AlerteDTO dto = new AlerteDTO();

        dto.setId(alerte.getId());
        dto.setTypeAlerte(alerte.getTypeAlerte());
        dto.setMessage(alerte.getMessage());
        dto.setDateCreation(alerte.getDateCreation());

        if (alerte.getVendeur() != null) {
            dto.setVendeurId(alerte.getVendeur().getId());
            dto.setVendeurNom(alerte.getVendeur().getNom());
        }

        return dto;
    }
}
