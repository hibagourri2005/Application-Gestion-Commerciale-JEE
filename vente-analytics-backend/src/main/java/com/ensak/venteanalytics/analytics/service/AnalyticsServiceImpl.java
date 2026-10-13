package com.ensak.venteanalytics.analytics.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.ensak.venteanalytics.analytics.dto.*;
import com.ensak.venteanalytics.analytics.repository.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private final VenteAnalyticsRepository venteRepo;
    private final AvisAnalyticsRepository avisRepo;
    private final FavoriAnalyticsRepository favoriRepo;
    private final HistoriqueRechercheAnalyticsRepository rechercheRepo;
    private final AnalyticsRepository analyticsRepository;
    private final ProduitAnalyticsRepository produitAnalyticsRepository;

    // ================= KPI =================
    @Override
    public KpiDTO getKpis() {
        return new KpiDTO(
                venteRepo.countVentes(),
                venteRepo.chiffreAffaire(),
                venteRepo.countProduits(),
                avisRepo.satisfactionMoyenne()
        );
    }

    // ================= VENTES =================
    @Override
    public List<VenteParCategorieDTO> ventesParCategorie() {
        return venteRepo.ventesParCategorie();
    }

    @Override
    public List<TopProduitDTO> topProduits() {
        return venteRepo.topProduits();
    }

    @Override
    public List<VenteTempsDTO> getVentesGroupByDate() {
        return analyticsRepository.getVentesGroupByDate();
    }

    // ================= AVIS =================
    @Override
    public List<AvisStatsDTO> statsAvis() {
        return avisRepo.statsAvis();
    }

    // ================= FAVORIS =================
    @Override
    public List<FavoriStatsDTO> topFavoris() {
        return favoriRepo.topFavoris();
    }

    // ================= RECHERCHES =================
    @Override
    public List<RechercheTopDTO> topRecherches() {
        return rechercheRepo.topRecherches();
    }

    // ================= PRODUITS (AVANCÉ) =================
    @Override
    public List<ProductScoreDTO> getProductScores() {
        return produitAnalyticsRepository.productScores();
    }

    @Override
    public List<ProduitAdvancedDTO> getAdvancedProduits() {
        return produitAnalyticsRepository.produitAdvancedStats();
    }
}
