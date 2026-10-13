package com.ensak.venteanalytics.repository;

import com.ensak.venteanalytics.dto.CategorieMontantDTO;
import com.ensak.venteanalytics.dto.CategorieVenteDTO;
import com.ensak.venteanalytics.entity.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {

    boolean existsByNom(String nom);

    // 🔹 Chiffre d'affaires par catégorie
    @Query("""
        SELECT new com.ensak.venteanalytics.dto.CategorieMontantDTO(c.nom, SUM(v.prixTotal))
        FROM Categorie c
        JOIN c.produits p
        JOIN p.ventes v
        GROUP BY c.nom
        ORDER BY SUM(v.prixTotal) DESC
    """)
    List<CategorieMontantDTO> findChiffreAffairesParCategorie();

    // 🔹 Ventes par catégorie (quantité totale vendue)
    @Query("""
        SELECT new com.ensak.venteanalytics.dto.CategorieVenteDTO(c.nom, SUM(v.quantite))
        FROM Categorie c
        JOIN c.produits p
        JOIN p.ventes v
        GROUP BY c.nom
        ORDER BY SUM(v.quantite) DESC
    """)
    List<CategorieVenteDTO> findVentesParCategorie();
}