package com.ensak.venteanalytics.service.impl;

import com.ensak.venteanalytics.dto.PredictionDTO;
import com.ensak.venteanalytics.entity.Analyste;
import com.ensak.venteanalytics.entity.Prediction;
import com.ensak.venteanalytics.entity.Produit;
import com.ensak.venteanalytics.mapper.PredictionMapper;
import com.ensak.venteanalytics.repository.AnalysteRepository;
import com.ensak.venteanalytics.repository.PredictionRepository;
import com.ensak.venteanalytics.repository.ProduitRepository;
import com.ensak.venteanalytics.service.interfaces.PredictionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PredictionServiceImpl implements PredictionService {

    private final PredictionRepository predictionRepository;
    private final ProduitRepository produitRepository;
    private final AnalysteRepository analysteRepository;

    public PredictionServiceImpl(PredictionRepository predictionRepository,
                                 ProduitRepository produitRepository,
                                 AnalysteRepository analysteRepository) {
        this.predictionRepository = predictionRepository;
        this.produitRepository = produitRepository;
        this.analysteRepository = analysteRepository;
    }

    @Override
    public List<PredictionDTO> getAllPredictions() {
        return predictionRepository.findAll().stream()
                .map(PredictionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PredictionDTO getPredictionById(Long id) {
        Prediction prediction = predictionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prediction non trouvée"));
        return PredictionMapper.toDTO(prediction);
    }

    @Override
    public List<PredictionDTO> getPredictionsByProduitId(Long produitId) {
        return predictionRepository.findByProduitId(produitId).stream()
                .map(PredictionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PredictionDTO> getPredictionsByAnalysteId(Long analysteId) {
        return predictionRepository.findByAnalysteId(analysteId).stream()
                .map(PredictionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PredictionDTO createPrediction(PredictionDTO dto, Long produitId, Long analysteId) {
        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
        Analyste analyste = analysteRepository.findById(analysteId)
                .orElseThrow(() -> new RuntimeException("Analyste non trouvé"));

        Prediction prediction = PredictionMapper.toEntity(dto, produit, analyste);

        Prediction saved = predictionRepository.save(prediction);
        return PredictionMapper.toDTO(saved);
    }

    @Override
    public PredictionDTO updatePrediction(Long id, PredictionDTO dto) {
        Prediction existing = predictionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prediction non trouvée"));

        existing.setDatePrediction(dto.getDatePrediction());
        existing.setValeurPrediction(dto.getValeurPrediction());
        // Update produit and analyste if needed - assuming ids are not changed here

        Prediction saved = predictionRepository.save(existing);
        return PredictionMapper.toDTO(saved);
    }

    @Override
    public void deletePrediction(Long id) {
        predictionRepository.deleteById(id);
    }
}
