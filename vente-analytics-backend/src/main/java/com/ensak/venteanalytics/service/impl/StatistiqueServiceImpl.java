package com.ensak.venteanalytics.service.impl;

import com.ensak.venteanalytics.dto.StatistiquesDTO;
import com.ensak.venteanalytics.dto.TopProduitDTO;
import com.ensak.venteanalytics.entity.Vendeur;
import com.ensak.venteanalytics.repository.*;
import com.ensak.venteanalytics.service.interfaces.StatistiqueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatistiqueServiceImpl implements StatistiqueService {

    private final VenteRepository venteRepository;
    private final ProduitRepository produitRepository;
    private final CategorieRepository categorieRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final FavoriRepository favoriRepository;
    private final VendeurRepository vendeurRepository;

    public StatistiqueServiceImpl(
            VenteRepository venteRepository,
            ProduitRepository produitRepository,
            CategorieRepository categorieRepository,
            UtilisateurRepository utilisateurRepository,
            FavoriRepository favoriRepository,
            VendeurRepository vendeurRepository
    ) {
        this.venteRepository = venteRepository;
        this.produitRepository = produitRepository;
        this.categorieRepository = categorieRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.favoriRepository = favoriRepository;
        this.vendeurRepository = vendeurRepository;
    }

    @Override
    public StatistiquesDTO getStatistiquesAdmin() {
        StatistiquesDTO dto = new StatistiquesDTO();

        // ===== Totaux =====
        dto.setChiffreAffairesTotal(
                venteRepository.chiffreAffairesTotal() != null ? venteRepository.chiffreAffairesTotal() : 0.0
        );
        dto.setTotalVentes(
                venteRepository.quantiteTotaleVendue() != null ? venteRepository.quantiteTotaleVendue() : 0L
        );
        dto.setTotalProduits(produitRepository.count());
        dto.setTotalCategories(categorieRepository.count());
        dto.setTotalUtilisateurs(utilisateurRepository.count());

        // ===== Top vendeur =====
        List<Long> topVendeurIds = utilisateurRepository.findVendeurIdsOrdreParVentesDesc();
        if (!topVendeurIds.isEmpty()) {
            vendeurRepository.findById(topVendeurIds.get(0)).ifPresent(v -> dto.setVendeurTop(v.getNom()));
        }

        // ===== Top produits =====
        dto.setTopProduitsFavoris(favoriRepository.findTopProduitsFavoris());
        dto.setTopProduitsVentes(produitRepository.findTopProduitsVentes());

        // ===== Graphiques supplémentaires =====
        dto.setChiffreAffairesParCategorie(categorieRepository.findChiffreAffairesParCategorie());
        dto.setVentesParCategorie(categorieRepository.findVentesParCategorie());
        dto.setTopVendeurs(utilisateurRepository.findTopVendeurs());

        return dto;
    }
}