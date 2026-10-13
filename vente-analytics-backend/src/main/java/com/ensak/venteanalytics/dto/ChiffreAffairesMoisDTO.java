package com.ensak.venteanalytics.dto;

public class ChiffreAffairesMoisDTO {
    private String mois;
    private Double montant;

    public ChiffreAffairesMoisDTO(String mois, Double montant) {
        this.mois = mois;
        this.montant = montant;
    }

    // ===== Getters / Setters =====
    public String getMois() { return mois; }
    public void setMois(String mois) { this.mois = mois; }

    public Double getMontant() { return montant; }
    public void setMontant(Double montant) { this.montant = montant; }
}