package com.ensak.venteanalytics.mapper;

import com.ensak.venteanalytics.dto.ProduitDTO;
import com.ensak.venteanalytics.entity.Categorie;
import com.ensak.venteanalytics.entity.Produit;

public class ProduitMapper {

    public static ProduitDTO toDTO(Produit produit) {
        ProduitDTO dto = new ProduitDTO();
        dto.setId(produit.getId());
        dto.setNom(produit.getNom());
        if (produit.getCategorie() != null) {
            dto.setCategorieId(produit.getCategorie().getId());
            dto.setCategorieNom(produit.getCategorie().getNom());
        }
        dto.setPrix(produit.getPrix());
        dto.setStock(produit.getStock());
        return dto;
    }

    public static Produit toEntity(ProduitDTO dto) {
        Produit produit = new Produit();
        produit.setId(dto.getId());
        produit.setNom(dto.getNom());

        if (dto.getCategorieId() != null) {
            Categorie cat = new Categorie();
            cat.setId(dto.getCategorieId());
            // Pas besoin de set nom ici, on suppose qu'on référence uniquement par id
            produit.setCategorie(cat);
        } else {
            produit.setCategorie(null);
        }

        produit.setPrix(dto.getPrix());
        produit.setStock(dto.getStock());
        return produit;
    }
}
