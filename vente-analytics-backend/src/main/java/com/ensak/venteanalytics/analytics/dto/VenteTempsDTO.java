package com.ensak.venteanalytics.analytics.dto;

import java.time.LocalDate;

public class VenteTempsDTO {
    private LocalDate date;
    private Long countVentes;
    private Double totalCA;

    public VenteTempsDTO(LocalDate date, Long countVentes, Double totalCA) {
        this.date = date;
        this.countVentes = countVentes;
        this.totalCA = totalCA;
    }

    public LocalDate getDate() { return date; }
    public Long getCountVentes() { return countVentes; }
    public Double getTotalCA() { return totalCA; }
}
