package com.ensak.venteanalytics.analytics.repository;


import com.ensak.venteanalytics.entity.Utilisateur;
import com.ensak.venteanalytics.enumtype.RoleUtilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UtilisateurAnalyticsRepository extends JpaRepository<Utilisateur, Long> {

    // 👥 KPI : Nombre acheteurs
    @Query("SELECT COUNT(u) FROM Utilisateur u WHERE u.role = :role")
    Long countByRole(RoleUtilisateur role);
}

