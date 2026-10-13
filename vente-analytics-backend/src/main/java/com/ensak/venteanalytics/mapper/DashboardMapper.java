package com.ensak.venteanalytics.mapper;

import com.ensak.venteanalytics.dto.DashboardDTO;

public class DashboardMapper {

    private DashboardMapper() {
        // empêcher l'instanciation
    }

    public static DashboardDTO toDTO(int totalProduits,
                                     int nombreVentes,
                                     double chiffreAffaires,
                                     double prixMoyen,
                                     double noteMoyenne) {

        return new DashboardDTO(
                totalProduits,
                nombreVentes,
                chiffreAffaires,
                prixMoyen,
                noteMoyenne
        );
    }
}
