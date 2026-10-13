package com.ensak.venteanalytics.analytics.repository;

import com.ensak.venteanalytics.entity.Vente;
import com.ensak.venteanalytics.analytics.dto.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VenteAnalyticsRepository extends JpaRepository<Vente, Long> {

    // 🔹 Nombre total de ventes
    @Query("SELECT COUNT(v) FROM Vente v")
    Long countVentes();

    // 🔹 Chiffre d'affaire global
    @Query("SELECT COALESCE(SUM(v.prixTotal), 0) FROM Vente v")
    Double chiffreAffaire();

    // 🔹 Nombre total de produits distincts
    @Query("SELECT COUNT(DISTINCT v.produit.id) FROM Vente v")
    Long countProduits();

    // 🔹 Ventes par catégorie
    @Query("""
        SELECT new com.ensak.venteanalytics.analytics.dto.VenteParCategorieDTO(
            v.produit.categorie.nom,
            COUNT(v)
        )
        FROM Vente v
        GROUP BY v.produit.categorie.nom
    """)
    List<VenteParCategorieDTO> ventesParCategorie();

    // 🔹 Top produits (quantité + chiffre d'affaire)
    @Query("""
        SELECT new com.ensak.venteanalytics.analytics.dto.TopProduitDTO(
            v.produit.nom,
            SUM(v.quantite),
            SUM(v.prixTotal)
        )
        FROM Vente v
        GROUP BY v.produit.nom
        ORDER BY SUM(v.quantite) DESC
    """)
    List<TopProduitDTO> topProduits();
}
