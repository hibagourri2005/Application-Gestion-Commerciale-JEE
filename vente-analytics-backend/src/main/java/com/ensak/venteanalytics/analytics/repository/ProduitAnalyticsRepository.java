package com.ensak.venteanalytics.analytics.repository;

import com.ensak.venteanalytics.analytics.dto.ProductScoreDTO;
import com.ensak.venteanalytics.analytics.dto.ProduitAdvancedDTO;
import com.ensak.venteanalytics.analytics.dto.TopProduitDTO;
import com.ensak.venteanalytics.entity.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProduitAnalyticsRepository extends JpaRepository<Vente, Long> {

	@Query("""
			SELECT new com.ensak.venteanalytics.analytics.dto.TopProduitDTO(
			    p.nom,
			    COALESCE(SUM(v.quantite), 0),
			    COALESCE(SUM(v.prixTotal), 0)
			)
			FROM Vente v
			JOIN v.produit p
			GROUP BY p.nom
			ORDER BY SUM(v.prixTotal) DESC
			""")
    List<TopProduitDTO> topProduits();
	
	// 🔹 Score produit
	@Query("""
	    SELECT new com.ensak.venteanalytics.analytics.dto.ProductScoreDTO(
	        p.id,
	        p.nom,
	        COALESCE(AVG(a.note),0),
	        COUNT(DISTINCT f.id),
	        COUNT(DISTINCT v.id),
	        (
	            COALESCE(AVG(a.note),0) * 0.5 +
	            COUNT(DISTINCT f.id) * 0.3 +
	            COUNT(DISTINCT v.id) * 0.2
	        )
	    )
	    FROM Produit p
	    LEFT JOIN Avis a ON a.produit = p
	    LEFT JOIN Favori f ON f.produit = p
	    LEFT JOIN Vente v ON v.produit = p
	    GROUP BY p.id, p.nom
	""")
	List<ProductScoreDTO> productScores();


	// 🔹 Tableau avancé produits
	@Query("""
	    SELECT new com.ensak.venteanalytics.analytics.dto.ProduitAdvancedDTO(
	        p.id,
	        p.nom,
	        p.categorie,
	        p.prix,
	        COUNT(DISTINCT v.id),
	        COALESCE(SUM(v.prixTotal),0),
	        COALESCE(AVG(a.note),0),
	        COUNT(DISTINCT f.id)
	    )
	    FROM Produit p
	    LEFT JOIN Vente v ON v.produit = p
	    LEFT JOIN Avis a ON a.produit = p
	    LEFT JOIN Favori f ON f.produit = p
	    GROUP BY p.id, p.nom, p.categorie, p.prix
	""")
	List<ProduitAdvancedDTO> produitAdvancedStats();

}
