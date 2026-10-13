package com.ensak.venteanalytics.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PredictionDTO {

    private Long id;
    private LocalDate datePrediction;
    private Double valeurPrediction;
    private Long produitId;
    private Long analysteId;
}
