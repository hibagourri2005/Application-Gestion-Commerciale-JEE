package com.ensak.venteanalytics.dto;

public class CategorieMoyenneDTO {
    private String categorie;
    private Double chiffreAffairesMoyen;

    public CategorieMoyenneDTO(String categorie, Double chiffreAffairesMoyen) {
        this.categorie = categorie;
        this.chiffreAffairesMoyen = chiffreAffairesMoyen;
    }

    // ===== Getters / Setters =====
    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }

    public Double getChiffreAffairesMoyen() { return chiffreAffairesMoyen; }
    public void setChiffreAffairesMoyen(Double chiffreAffairesMoyen) { this.chiffreAffairesMoyen = chiffreAffairesMoyen; }
}