package com.ensak.venteanalytics.analytics.repository;

import com.ensak.venteanalytics.entity.Favori;
import com.ensak.venteanalytics.analytics.dto.FavoriStatsDTO;
import org.springframework.data.jpa.repository.*;
import java.util.List;

public interface FavoriAnalyticsRepository extends JpaRepository<Favori, Long> {

    @Query("""
        SELECT new com.ensak.venteanalytics.analytics.dto.FavoriStatsDTO(
            f.produit.nom, COUNT(f)
        )
        FROM Favori f
        GROUP BY f.produit.nom
        ORDER BY COUNT(f) DESC
    """)
    List<FavoriStatsDTO> topFavoris();
}
