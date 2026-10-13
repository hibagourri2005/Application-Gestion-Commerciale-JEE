package com.ensak.venteanalytics.repository;

import com.ensak.venteanalytics.entity.Analyste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysteRepository extends JpaRepository<Analyste, Long> {
}
