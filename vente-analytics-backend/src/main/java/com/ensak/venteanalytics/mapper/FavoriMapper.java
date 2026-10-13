package com.ensak.venteanalytics.mapper;

import java.time.LocalDate;

import com.ensak.venteanalytics.dto.FavoriDTO;
import com.ensak.venteanalytics.entity.Favori;
import com.ensak.venteanalytics.entity.Acheteur;
import com.ensak.venteanalytics.entity.Produit;

public class FavoriMapper {

	public static FavoriDTO toDTO(Favori favori) {
	    return FavoriDTO.builder()
	            .id(favori.getId())
	            .dateAjout(favori.getDateAjout())
	            .acheteurId(favori.getAcheteur().getId())
	            .produitId(favori.getProduit().getId())
	            .produitNom(favori.getProduit().getNom()) // ⚡ Ajouter le nom ici
	            .build();
	}


    public static Favori toEntity(Acheteur acheteur, Produit produit) {
        Favori favori = new Favori();
        favori.setAcheteur(acheteur);
        favori.setProduit(produit);
        favori.setDateAjout(LocalDate.now());
        return favori;
    }
}
