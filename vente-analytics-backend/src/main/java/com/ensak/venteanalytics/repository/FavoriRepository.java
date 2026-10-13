package com.ensak.venteanalytics.repository;

import com.ensak.venteanalytics.dto.TopProduitDTO;
import com.ensak.venteanalytics.entity.Favori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FavoriRepository extends JpaRepository<Favori, Long> {

    List<Favori> findByAcheteurId(Long acheteurId);

    List<Favori> findByProduitId(Long produitId);

    Optional<Favori> findByAcheteurIdAndProduitId(Long acheteurId, Long produitId);
   
 // ✅ TOP produits favoris (STATISTIQUE ADMIN)
    @Query("SELECT new com.ensak.venteanalytics.dto.TopProduitDTO(p.id, p.nom, COUNT(f.id), true) " +
            "FROM Favori f " +
            "JOIN f.produit p " +
            "GROUP BY p.id, p.nom " +
            "ORDER BY COUNT(f.id) DESC")
     List<TopProduitDTO> findTopProduitsFavoris();
    
}
