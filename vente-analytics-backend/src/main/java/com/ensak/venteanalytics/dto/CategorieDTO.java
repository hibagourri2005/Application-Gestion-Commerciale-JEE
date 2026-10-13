package com.ensak.venteanalytics.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategorieDTO {

    private Long id;
    private String nom;
    private String description;

    // Liste simplifiée des produits (id + nom) pour éviter grosse surcharge JSON
    private List<ProduitInfo> produits;

    @Getter
    @Setter
    public static class ProduitInfo {
        private Long id;
        private String nom;
    }
/**    
 // ======= Classe interne ProduitInfo avec getters/setters manuels =======
    public static class ProduitInfo {
        private Long id;
        private String nom;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getNom() { return nom; }
        public void setNom(String nom) { this.nom = nom; }
    } **/
}
