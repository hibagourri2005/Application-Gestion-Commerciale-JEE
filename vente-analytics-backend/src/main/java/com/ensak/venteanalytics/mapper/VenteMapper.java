package com.ensak.venteanalytics.mapper;

import com.ensak.venteanalytics.dto.VenteDTO;
import com.ensak.venteanalytics.entity.Vente;

public class VenteMapper {

    public static VenteDTO toDTO(Vente vente) {
        VenteDTO dto = new VenteDTO();

        dto.setId(vente.getId());
        dto.setDateVente(vente.getDateVente());
        dto.setQuantite(vente.getQuantite());
        dto.setPrixTotal(vente.getPrixTotal());

        if (vente.getProduit() != null) {
            dto.setProduitId(vente.getProduit().getId());
            dto.setProduitNom(vente.getProduit().getNom());
        }

        if (vente.getVendeur() != null) {
            dto.setVendeurId(vente.getVendeur().getId());
            dto.setVendeurNom(vente.getVendeur().getNom());
        }

        return dto;
    }

    // Méthode inverse (DTO -> entity) pour créer ou modifier une vente, si besoin
    public static Vente toEntity(VenteDTO dto, Vente vente) {
        if (vente == null) {
            vente = new Vente();
        }

        vente.setQuantite(dto.getQuantite());
        vente.setPrixTotal(dto.getPrixTotal());
        vente.setDateVente(dto.getDateVente());

        // Pour produit et vendeur, on les mettra en service car il faut récupérer entités complètes

        return vente;
    }
}
