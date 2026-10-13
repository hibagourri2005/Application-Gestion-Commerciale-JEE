package com.ensak.venteanalytics.analytics.dto;

public class TopProduitDTO {

    private String produit;
    private Long quantiteVendue;
    private Double chiffreAffaire;

    public TopProduitDTO(String produit, Long quantiteVendue, Double chiffreAffaire) {
        this.produit = produit;
        this.quantiteVendue = quantiteVendue;
        this.chiffreAffaire = chiffreAffaire;
    }

    public String getProduit() {
        return produit;
    }

    public Long getQuantiteVendue() {
        return quantiteVendue;
    }

    public Double getChiffreAffaire() {
        return chiffreAffaire;
    }
}
