package com.ensak.venteanalytics.dto;

public class CategorieMontantDTO {
    private String categorie;
    private Double montant;

    public CategorieMontantDTO(String categorie, Double montant) {
        this.categorie = categorie;
        this.montant = montant;
    }

    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }

    public Double getMontant() { return montant; }
    public void setMontant(Double montant) { this.montant = montant; }
}