package com.ensak.venteanalytics.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProduitDTO {

    private Long id;
    private String nom;
    private Long categorieId;   // id de la catégorie liée
    private String categorieNom; // nom de la catégorie (optionnel)
    private Double prix;
    private Integer stock;
}
