package com.ensak.venteanalytics.service.interfaces;

import com.ensak.venteanalytics.dto.FavoriDTO;

import java.util.List;

public interface FavoriService {

    FavoriDTO ajouterFavori(Long acheteurId, Long produitId);

    void supprimerFavori(Long favoriId);

    void supprimerFavoriParProduit(Long acheteurId, Long produitId);

    List<FavoriDTO> getFavorisParAcheteur(Long acheteurId);
}
