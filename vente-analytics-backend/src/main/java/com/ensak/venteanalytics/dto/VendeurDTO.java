package com.ensak.venteanalytics.dto;

public class VendeurDTO {
    private String nom;
    private Double totalVentes; // <-- Double au lieu de Long

    public VendeurDTO(String nom, Double totalVentes) {
        this.nom = nom;
        this.totalVentes = totalVentes;
    }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public Double getTotalVentes() { return totalVentes; }
    public void setTotalVentes(Double totalVentes) { this.totalVentes = totalVentes; }
}