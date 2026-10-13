package com.ensak.venteanalytics.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

import lombok.*;
@Entity
@Getter
@Setter
@Table(name = "favori")
public class Favori {

    public LocalDate getDateAjout() {
		return dateAjout;
	}

	public void setDateAjout(LocalDate dateAjout) {
		this.dateAjout = dateAjout;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateAjout;

    // 🔗 Relation : plusieurs favoris pour un acheteur
    @ManyToOne
    @JoinColumn(name = "acheteur_id", nullable = false)
    private Acheteur acheteur;

    // 🔗 Relation : un produit peut être favori plusieurs fois
    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

    // =====================
    // CONSTRUCTEURS
    // =====================
    public Favori() {
    }

    public Favori(Acheteur acheteur, Produit produit) {
        this.acheteur = acheteur;
        this.produit = produit;
    }

    // =====================
    // GETTERS & SETTERS
    // =====================
    public Long getId() {
        return id;
    }

    public Acheteur getAcheteur() {
        return acheteur;
    }

    public void setAcheteur(Acheteur acheteur) {
        this.acheteur = acheteur;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}