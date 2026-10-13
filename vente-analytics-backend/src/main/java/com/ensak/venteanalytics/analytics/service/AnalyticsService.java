package com.ensak.venteanalytics.analytics.service;

import com.ensak.venteanalytics.analytics.dto.*;
import java.util.List;

public interface AnalyticsService {

    KpiDTO getKpis();
    List<VenteParCategorieDTO> ventesParCategorie();
    List<TopProduitDTO> topProduits();
    List<AvisStatsDTO> statsAvis();
    List<FavoriStatsDTO> topFavoris();
    List<RechercheTopDTO> topRecherches();
    List<VenteTempsDTO> getVentesGroupByDate();
    List<ProductScoreDTO> getProductScores();
    List<ProduitAdvancedDTO> getAdvancedProduits();


}
