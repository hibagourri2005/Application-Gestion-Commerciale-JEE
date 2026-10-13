package com.ensak.venteanalytics.repository;

import com.ensak.venteanalytics.dto.TopProduitDTO;
import com.ensak.venteanalytics.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

    // Top produits vendus
    @Query("SELECT p FROM Produit p LEFT JOIN p.ventes v GROUP BY p ORDER BY SUM(v.quantite) DESC")
    List<Produit> findTopProduits();
    

    @Query("SELECT new com.ensak.venteanalytics.dto.TopProduitDTO(p.id, p.nom, SUM(v.quantite)) " +
            "FROM Produit p " +
            "LEFT JOIN p.ventes v " +
            "GROUP BY p.id, p.nom " +
            "ORDER BY SUM(v.quantite) DESC")
     List<TopProduitDTO> findTopProduitsVentes();

}