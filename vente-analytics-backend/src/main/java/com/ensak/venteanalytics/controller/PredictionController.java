package com.ensak.venteanalytics.controller;

import com.ensak.venteanalytics.dto.PredictionDTO;
import com.ensak.venteanalytics.service.interfaces.PredictionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predictions")
public class PredictionController {

    private final PredictionService predictionService;

    public PredictionController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @GetMapping
    public List<PredictionDTO> getAllPredictions() {
        return predictionService.getAllPredictions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PredictionDTO> getPredictionById(@PathVariable Long id) {
        return ResponseEntity.ok(predictionService.getPredictionById(id));
    }

    @GetMapping("/produit/{produitId}")
    public List<PredictionDTO> getPredictionsByProduit(@PathVariable Long produitId) {
        return predictionService.getPredictionsByProduitId(produitId);
    }

    @GetMapping("/analyste/{analysteId}")
    public List<PredictionDTO> getPredictionsByAnalyste(@PathVariable Long analysteId) {
        return predictionService.getPredictionsByAnalysteId(analysteId);
    }

    @PostMapping
    public ResponseEntity<PredictionDTO> createPrediction(
            @RequestBody PredictionDTO dto,
            @RequestParam Long produitId,
            @RequestParam Long analysteId) {
        PredictionDTO created = predictionService.createPrediction(dto, produitId, analysteId);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PredictionDTO> updatePrediction(
            @PathVariable Long id,
            @RequestBody PredictionDTO dto) {
        PredictionDTO updated = predictionService.updatePrediction(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrediction(@PathVariable Long id) {
        predictionService.deletePrediction(id);
        return ResponseEntity.noContent().build();
    }
}
