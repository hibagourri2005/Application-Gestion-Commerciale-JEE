package com.ensak.venteanalytics.mapper;

import com.ensak.venteanalytics.dto.AvisDTO;
import com.ensak.venteanalytics.entity.Avis;
import com.ensak.venteanalytics.entity.Produit;
import com.ensak.venteanalytics.entity.Acheteur;

public class AvisMapper {

    public static AvisDTO toDTO(Avis avis) {
        return AvisDTO.builder()
                .id(avis.getId())
                .note(avis.getNote())
                .commentaire(avis.getCommentaire())
                .dateCreation(avis.getDateCreation())
                .produitId(avis.getProduit() != null ? avis.getProduit().getId() : null)
                .acheteurId(avis.getAcheteur() != null ? avis.getAcheteur().getId() : null)
                .build();
    }

    public static Avis toEntity(AvisDTO dto, Produit produit, Acheteur acheteur) {
        Avis avis = new Avis();
        avis.setId(dto.getId());
        avis.setNote(dto.getNote());
        avis.setCommentaire(dto.getCommentaire());
        avis.setDateCreation(dto.getDateCreation());
        avis.setProduit(produit);
        avis.setAcheteur(acheteur);
        return avis;
    }
}
