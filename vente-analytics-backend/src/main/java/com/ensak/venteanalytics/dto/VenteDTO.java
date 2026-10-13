package com.ensak.venteanalytics.dto;

import java.time.LocalDate;

import com.ensak.venteanalytics.enumtype.RoleUtilisateur;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VenteDTO {
    private Long id;
    private int quantite;
    private double prixTotal;
    private LocalDate dateVente;

    private Long produitId;
    private String produitNom;

    private Long vendeurId;
    private String vendeurNom;

    // getters et setters

    // constructeur vide
    public VenteDTO() {}

    public VenteDTO(Long id, int quantite, double prixTotal, LocalDate dateVente, Long produitId, String produitNom, Long vendeurId, String vendeurNom) {
        this.id = id;
        this.quantite = quantite;
        this.prixTotal = prixTotal;
        this.dateVente = dateVente;
        this.produitId = produitId;
        this.produitNom = produitNom;
        this.vendeurId = vendeurId;
        this.vendeurNom = vendeurNom;
    }

    // getters/setters...
}
