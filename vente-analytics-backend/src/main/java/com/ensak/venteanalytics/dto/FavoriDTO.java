package com.ensak.venteanalytics.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class FavoriDTO {

    private Long id;
    private LocalDate dateAjout;

    private Long acheteurId;
    private Long produitId;

    // Nouveau champ
    private String produitNom;
}

