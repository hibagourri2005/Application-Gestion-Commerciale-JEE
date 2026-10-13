package com.ensak.venteanalytics.dto;

public class CategorieVenteDTO {
    private String categorie;
    private Long quantite;

    public CategorieVenteDTO(String categorie, Long quantite) {
        this.categorie = categorie;
        this.quantite = quantite;
    }

    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }

    public Long getQuantite() { return quantite; }
    public void setQuantite(Long quantite) { this.quantite = quantite; }
}