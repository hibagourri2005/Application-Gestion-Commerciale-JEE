package com.ensak.venteanalytics.analytics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProduitAdvancedDTO {

    private Long produitId;
    private String nom;
    private String categorie;
    private Double prix;

    private Long ventes;
    private Double chiffreAffaires;
    private Double noteMoyenne;
    private Long favoris;
}
