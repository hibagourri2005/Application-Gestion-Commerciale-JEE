package com.ensak.venteanalytics.analytics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProduitAnalyseDTO {
    private String produit;
    private Double chiffreAffaire;
    private Long quantiteVendue;
}
