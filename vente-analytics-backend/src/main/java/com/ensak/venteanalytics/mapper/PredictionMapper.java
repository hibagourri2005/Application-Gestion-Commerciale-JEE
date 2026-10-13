package com.ensak.venteanalytics.mapper;

import com.ensak.venteanalytics.dto.PredictionDTO;
import com.ensak.venteanalytics.entity.Analyste;
import com.ensak.venteanalytics.entity.Prediction;
import com.ensak.venteanalytics.entity.Produit;

public class PredictionMapper {

    public static PredictionDTO toDTO(Prediction entity) {
        return PredictionDTO.builder()
                .id(entity.getId())
                .datePrediction(entity.getDatePrediction())
                .valeurPrediction(entity.getValeurPrediction())
                .produitId(entity.getProduit() != null ? entity.getProduit().getId() : null)
                .analysteId(entity.getAnalyste() != null ? entity.getAnalyste().getId() : null)
                .build();
    }

    public static Prediction toEntity(PredictionDTO dto, Produit produit, Analyste analyste) {
        Prediction prediction = new Prediction();
        prediction.setId(dto.getId());
        prediction.setDatePrediction(dto.getDatePrediction());
        prediction.setValeurPrediction(dto.getValeurPrediction());
        prediction.setProduit(produit);
        prediction.setAnalyste(analyste);
        return prediction;
    }
}
