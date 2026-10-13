package com.ensak.venteanalytics.analytics.repository;

import com.ensak.venteanalytics.analytics.dto.VenteTempsDTO;
import com.ensak.venteanalytics.entity.Vente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Repository
public interface AnalyticsRepository extends JpaRepository<Vente, Long> {

    // CA et nombre de ventes groupés par date (jour)
    @Query("SELECT new com.ensak.venteanalytics.analytics.dto.VenteTempsDTO(v.dateVente, COUNT(v), SUM(v.prixTotal)) " +
           "FROM Vente v " +
           "GROUP BY v.dateVente " +
           "ORDER BY v.dateVente")
    List<VenteTempsDTO> getVentesGroupByDate();

} 
