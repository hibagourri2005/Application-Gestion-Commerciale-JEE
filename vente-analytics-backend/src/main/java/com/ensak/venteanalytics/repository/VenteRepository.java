package com.ensak.venteanalytics.repository;

import com.ensak.venteanalytics.entity.Vente;
import com.ensak.venteanalytics.entity.Produit;
import com.ensak.venteanalytics.entity.Vendeur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface VenteRepository extends JpaRepository<Vente, Long> {

    List<Vente> findByProduit(Produit produit);

    List<Vente> findByVendeur(Vendeur vendeur);

    List<Vente> findByDateVenteBetween(LocalDate debut, LocalDate fin);

    // 🔢 Chiffre d'affaires global
    @Query("SELECT SUM(v.prixTotal) FROM Vente v")
    Double chiffreAffairesTotal();

    // 📊 Quantité totale vendue
    @Query("SELECT SUM(v.quantite) FROM Vente v")
    Long quantiteTotaleVendue();

    // 🔝 Produits les plus vendus
    @Query("""
        SELECT v.produit, SUM(v.quantite)
        FROM Vente v
        GROUP BY v.produit
        ORDER BY SUM(v.quantite) DESC
    """)
    List<Object[]> topProduitsVendus();

    // 📅 Ventes par date
    @Query("""
        SELECT v.dateVente, SUM(v.prixTotal)
        FROM Vente v
        GROUP BY v.dateVente
        ORDER BY v.dateVente
    """)
    List<Object[]> ventesParDate();
}
