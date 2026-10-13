package com.ensak.venteanalytics.mapper;

import com.ensak.venteanalytics.dto.StatistiqueVenteDTO;

public class StatistiqueVenteMapper {

    private StatistiqueVenteMapper() {
        // empêcher l'instanciation
    }

    public static StatistiqueVenteDTO toDTO(String periode,
                                            int ventesTotales,
                                            double chiffreAffaires) {

        StatistiqueVenteDTO dto = new StatistiqueVenteDTO();
        dto.setPeriode(periode);
        dto.setVentesTotales(ventesTotales);
        dto.setChiffreAffaires(chiffreAffaires);

        return dto;
    }
}
