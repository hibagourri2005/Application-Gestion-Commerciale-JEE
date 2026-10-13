package com.ensak.venteanalytics.service.interfaces;

import com.ensak.venteanalytics.dto.PredictionDTO;

import java.util.List;

public interface PredictionService {

    List<PredictionDTO> getAllPredictions();

    PredictionDTO getPredictionById(Long id);

    List<PredictionDTO> getPredictionsByProduitId(Long produitId);

    List<PredictionDTO> getPredictionsByAnalysteId(Long analysteId);

    PredictionDTO createPrediction(PredictionDTO dto, Long produitId, Long analysteId);

    PredictionDTO updatePrediction(Long id, PredictionDTO dto);

    void deletePrediction(Long id);
}
