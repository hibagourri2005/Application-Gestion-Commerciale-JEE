package com.ensak.venteanalytics.dto;

import java.util.List;

public class StatistiquesDTO {

    // ===== Totaux =====
    private Double chiffreAffairesTotal;
    private Long totalVentes;
    private Long totalProduits;
    private Long totalCategories;
    private Long totalUtilisateurs;

    // ===== Top entités =====
    private String vendeurTop;
    private List<TopProduitDTO> topProduitsFavoris;
    private List<TopProduitDTO> topProduitsVentes;

    // ===== Graphiques supplémentaires =====
    private List<CategorieMontantDTO> chiffreAffairesParCategorie; // Chiffre d'affaires par catégorie
    private List<CategorieVenteDTO> ventesParCategorie;            // Ventes par catégorie
    private List<VendeurDTO> topVendeurs;                          // Top vendeurs

    // ===== Getters / Setters =====

    public Double getChiffreAffairesTotal() { return chiffreAffairesTotal; }
    public void setChiffreAffairesTotal(Double chiffreAffairesTotal) { this.chiffreAffairesTotal = chiffreAffairesTotal; }

    public Long getTotalVentes() { return totalVentes; }
    public void setTotalVentes(Long totalVentes) { this.totalVentes = totalVentes; }

    public Long getTotalProduits() { return totalProduits; }
    public void setTotalProduits(Long totalProduits) { this.totalProduits = totalProduits; }

    public Long getTotalCategories() { return totalCategories; }
    public void setTotalCategories(Long totalCategories) { this.totalCategories = totalCategories; }

    public Long getTotalUtilisateurs() { return totalUtilisateurs; }
    public void setTotalUtilisateurs(Long totalUtilisateurs) { this.totalUtilisateurs = totalUtilisateurs; }

    public String getVendeurTop() { return vendeurTop; }
    public void setVendeurTop(String vendeurTop) { this.vendeurTop = vendeurTop; }

    public List<TopProduitDTO> getTopProduitsFavoris() { return topProduitsFavoris; }
    public void setTopProduitsFavoris(List<TopProduitDTO> topProduitsFavoris) { this.topProduitsFavoris = topProduitsFavoris; }

    public List<TopProduitDTO> getTopProduitsVentes() { return topProduitsVentes; }
    public void setTopProduitsVentes(List<TopProduitDTO> topProduitsVentes) { this.topProduitsVentes = topProduitsVentes; }

    public List<CategorieMontantDTO> getChiffreAffairesParCategorie() { return chiffreAffairesParCategorie; }
    public void setChiffreAffairesParCategorie(List<CategorieMontantDTO> chiffreAffairesParCategorie) { this.chiffreAffairesParCategorie = chiffreAffairesParCategorie; }

    public List<CategorieVenteDTO> getVentesParCategorie() { return ventesParCategorie; }
    public void setVentesParCategorie(List<CategorieVenteDTO> ventesParCategorie) { this.ventesParCategorie = ventesParCategorie; }

    public List<VendeurDTO> getTopVendeurs() { return topVendeurs; }
    public void setTopVendeurs(List<VendeurDTO> topVendeurs) { this.topVendeurs = topVendeurs; }
}