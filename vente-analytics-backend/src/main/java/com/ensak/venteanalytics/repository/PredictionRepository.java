package com.ensak.venteanalytics.repository;

import com.ensak.venteanalytics.entity.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredictionRepository extends JpaRepository<Prediction, Long> {
    
    List<Prediction> findByProduitId(Long produitId);

    List<Prediction> findByAnalysteId(Long analysteId);
}
