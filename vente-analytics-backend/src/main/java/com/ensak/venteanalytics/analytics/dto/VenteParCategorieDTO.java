package com.ensak.venteanalytics.analytics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VenteParCategorieDTO {
    private String categorie;
    private Long totalVentes;
}
