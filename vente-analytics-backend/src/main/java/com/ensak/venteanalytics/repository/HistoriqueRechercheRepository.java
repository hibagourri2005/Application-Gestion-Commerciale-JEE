package com.ensak.venteanalytics.repository;

import com.ensak.venteanalytics.entity.HistoriqueRecherche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoriqueRechercheRepository extends JpaRepository<HistoriqueRecherche, Long> {

    List<HistoriqueRecherche> findByAcheteurIdOrderByDateRechercheDesc(Long acheteurId);
}
