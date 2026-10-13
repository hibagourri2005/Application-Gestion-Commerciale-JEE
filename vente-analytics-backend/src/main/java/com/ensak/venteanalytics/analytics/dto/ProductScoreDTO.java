package com.ensak.venteanalytics.analytics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductScoreDTO {

    private Long produitId;
    private String nomProduit;

    private Double noteMoyenne;
    private Long nbFavoris;
    private Long nbVentes;

    private Double score;
}
