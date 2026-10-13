package com.ensak.venteanalytics.analytics.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.ensak.venteanalytics.analytics.service.*;
import com.ensak.venteanalytics.analytics.dto.*;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
@CrossOrigin
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/kpis")
    public KpiDTO kpis() {
        return analyticsService.getKpis();
    }

    @GetMapping("/ventes-par-categorie")
    public List<VenteParCategorieDTO> ventesParCategorie() {
        return analyticsService.ventesParCategorie();
    }

    @GetMapping("/top-produits")
    public List<TopProduitDTO> topProduits() {
        return analyticsService.topProduits();
    }

    @GetMapping("/avis")
    public List<AvisStatsDTO> avis() {
        return analyticsService.statsAvis();
    }

    @GetMapping("/favoris")
    public List<FavoriStatsDTO> favoris() {
        return analyticsService.topFavoris();
    }

    @GetMapping("/recherches")
    public List<RechercheTopDTO> recherches() {
        return analyticsService.topRecherches();
    }
    
    @GetMapping("/ventes-par-jour")
    public List<VenteTempsDTO> getVentesParJour() {
        return analyticsService.getVentesGroupByDate();
}
    @GetMapping("/products/scores")
    public List<ProductScoreDTO> productScores() {
        return analyticsService.getProductScores();
    }

    @GetMapping("/products/advanced")
    public List<ProduitAdvancedDTO> advancedProducts() {
        return analyticsService.getAdvancedProduits();
    }


}
