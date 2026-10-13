package com.ensak.venteanalytics.analytics.dto;

public class KpiDTO {

    private Long totalVentes;
    private Double chiffreAffaire;
    private Long totalProduits;
    private Double satisfactionMoyenne;

    public KpiDTO(Long totalVentes, Double chiffreAffaire, Long totalProduits, Double satisfactionMoyenne) {
        this.totalVentes = totalVentes;
        this.chiffreAffaire = chiffreAffaire;
        this.totalProduits = totalProduits;
        this.satisfactionMoyenne = satisfactionMoyenne;
    }

    public Long getTotalVentes() {
        return totalVentes;
    }

    public Double getChiffreAffaire() {
        return chiffreAffaire;
    }

    public Long getTotalProduits() {
        return totalProduits;
    }

    public Double getSatisfactionMoyenne() {
        return satisfactionMoyenne;
    }
}
