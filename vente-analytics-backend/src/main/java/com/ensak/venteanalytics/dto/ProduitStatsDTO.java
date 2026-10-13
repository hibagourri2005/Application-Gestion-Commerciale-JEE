package com.ensak.venteanalytics.dto;

public class ProduitStatsDTO {
    private String nom;
    private Double prixUnitaire;
    private Long stock;

    public ProduitStatsDTO(String nom, Double prixUnitaire, Long stock) {
        this.nom = nom;
        this.prixUnitaire = prixUnitaire;
        this.stock = stock;
    }

    // ===== Getters / Setters =====
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public Double getPrixUnitaire() { return prixUnitaire; }
    public void setPrixUnitaire(Double prixUnitaire) { this.prixUnitaire = prixUnitaire; }

    public Long getStock() { return stock; }
    public void setStock(Long stock) { this.stock = stock; }
}