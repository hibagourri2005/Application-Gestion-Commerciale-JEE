package com.ensak.venteanalytics.entity;

import java.time.LocalDate;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Vente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantite;
    private double prixTotal;
    private LocalDate dateVente;

    // Relation vers Produit (éviter boucle infinie)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produit_id")
    @JsonIgnore // On ignore dans l'entité pour éviter la sérialisation récursive
    private Produit produit;

  /**  // Relation vers Vendeur
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendeur_id")
    @JsonIgnore
    private Vendeur vendeur;
**/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendeur_id", nullable = false)
    private Utilisateur vendeur;


}
