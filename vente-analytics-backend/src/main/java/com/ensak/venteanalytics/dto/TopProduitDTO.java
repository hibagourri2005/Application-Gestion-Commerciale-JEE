package com.ensak.venteanalytics.dto;

import java.util.List;

public class TopProduitDTO {
    private Long id;
    private String nom;
    private Long quantiteVendue;  // Pour Top Ventes
    private Long nombreFavoris;   // Pour Top Favoris

    // Constructeur pour Ventes
    public TopProduitDTO(Long id, String nom, Long quantiteVendue) {
        this.id = id;
        this.nom = nom;
        this.quantiteVendue = quantiteVendue;
    }

    // Constructeur pour Favoris
    public TopProduitDTO(Long id, String nom, Long nombreFavoris, boolean favoris) {
        this.id = id;
        this.nom = nom;
        this.nombreFavoris = nombreFavoris;
    }

    // Getters / Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public Long getQuantiteVendue() { return quantiteVendue; }
    public void setQuantiteVendue(Long quantiteVendue) { this.quantiteVendue = quantiteVendue; }

    public Long getNombreFavoris() { return nombreFavoris; }
    public void setNombreFavoris(Long nombreFavoris) { this.nombreFavoris = nombreFavoris; }
}