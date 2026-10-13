package com.ensak.venteanalytics.analytics.repository;

import com.ensak.venteanalytics.entity.HistoriqueRecherche;
import com.ensak.venteanalytics.analytics.dto.*;
import org.springframework.data.jpa.repository.*;
import java.util.List;

public interface HistoriqueRechercheAnalyticsRepository extends JpaRepository<HistoriqueRecherche, Long> {

    @Query("SELECT COUNT(h) FROM HistoriqueRecherche h")
    Long totalRecherches();

    @Query("""
        SELECT new com.ensak.venteanalytics.analytics.dto.RechercheTopDTO(
            h.termeRecherche, COUNT(h)
        )
        FROM HistoriqueRecherche h
        GROUP BY h.termeRecherche
        ORDER BY COUNT(h) DESC
    """)
    List<RechercheTopDTO> topRecherches();
}
