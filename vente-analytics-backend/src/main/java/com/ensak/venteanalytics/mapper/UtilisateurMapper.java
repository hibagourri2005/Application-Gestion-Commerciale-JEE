package com.ensak.venteanalytics.mapper;

import com.ensak.venteanalytics.dto.UtilisateurDTO;
import com.ensak.venteanalytics.entity.Utilisateur;

public class UtilisateurMapper {

    public static UtilisateurDTO toDTO(Utilisateur utilisateur) {

        UtilisateurDTO dto = new UtilisateurDTO();
        dto.setId(utilisateur.getId());
        dto.setNom(utilisateur.getNom());
        dto.setEmail(utilisateur.getEmail());

        if (utilisateur.getRole() != null) {
            dto.setRole(utilisateur.getRole());
        }

        return dto;
    }
}
