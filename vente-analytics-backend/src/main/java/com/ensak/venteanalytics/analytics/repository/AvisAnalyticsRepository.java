package com.ensak.venteanalytics.analytics.repository;

import com.ensak.venteanalytics.entity.Avis;
import com.ensak.venteanalytics.analytics.dto.*;
import org.springframework.data.jpa.repository.*;
import java.util.List;

public interface AvisAnalyticsRepository extends JpaRepository<Avis, Long> {

    @Query("SELECT new com.ensak.venteanalytics.analytics.dto.AvisStatsDTO(a.note, COUNT(a)) FROM Avis a GROUP BY a.note")
    List<AvisStatsDTO> statsAvis();

    @Query("SELECT AVG(a.note) FROM Avis a")
    Double satisfactionMoyenne();
}
