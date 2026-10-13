package com.ensak.venteanalytics.service.impl;

import com.ensak.venteanalytics.dto.DashboardDTO;
import com.ensak.venteanalytics.dto.StatistiqueVenteDTO;
import com.ensak.venteanalytics.entity.Vente;
import com.ensak.venteanalytics.mapper.DashboardMapper;
import com.ensak.venteanalytics.mapper.StatistiqueVenteMapper;
import com.ensak.venteanalytics.repository.ProduitRepository;
import com.ensak.venteanalytics.repository.VenteRepository;
import com.ensak.venteanalytics.service.interfaces.AnalyseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalyseServiceImpl implements AnalyseService {

    private final ProduitRepository produitRepository;
    private final VenteRepository venteRepository;

    public AnalyseServiceImpl(ProduitRepository produitRepository,
                              VenteRepository venteRepository) {
        this.produitRepository = produitRepository;
        this.venteRepository = venteRepository;
    }

    /**
     * Génération du dashboard analytique global
     */
    @Override
    public DashboardDTO genererDashboard() {

        int totalProduits = (int) produitRepository.count();

        List<Vente> ventes = venteRepository.findAll();
        int nombreVentes = ventes.size();

        double chiffreAffaires = ventes.stream()
                .mapToDouble(Vente::getPrixTotal)
                .sum();

        double prixMoyen = nombreVentes == 0
                ? 0
                : chiffreAffaires / nombreVentes;

        double noteMoyenne = 0; // simulée (future analyse Avis)

        return DashboardMapper.toDTO(
                totalProduits,
                nombreVentes,
                chiffreAffaires,
                prixMoyen,
                noteMoyenne
        );
    }

    /**
     * Statistiques de ventes par période (simulation ENSA)
     */
    @Override
    public List<StatistiqueVenteDTO> statistiquesVentesParPeriode() {

        List<Vente> ventes = venteRepository.findAll();

        double chiffreAffaires = ventes.stream()
                .mapToDouble(Vente::getPrixTotal)
                .sum();

        List<StatistiqueVenteDTO> statistiques = new ArrayList<>();

        // Simulation simple (année courante)
        statistiques.add(
                StatistiqueVenteMapper.toDTO(
                        "2025",
                        ventes.size(),
                        chiffreAffaires
                )
        );

        return statistiques;
    }
}
