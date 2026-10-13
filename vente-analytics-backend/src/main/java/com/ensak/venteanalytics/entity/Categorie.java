package com.ensak.venteanalytics.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categorie")
@Getter
@Setter
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    private String description;

    // Relation vers produits
    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // Pour éviter boucle infinie JSON, on ignore produits dans la sérialisation de catégorie
    @com.fasterxml.jackson.annotation.JsonIgnore
    private List<Produit> produits;
}
