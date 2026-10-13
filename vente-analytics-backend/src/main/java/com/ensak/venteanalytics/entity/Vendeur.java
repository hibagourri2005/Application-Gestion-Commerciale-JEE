package com.ensak.venteanalytics.entity;

import java.util.List;

import com.ensak.venteanalytics.enumtype.RoleUtilisateur;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "vendeur")
@PrimaryKeyJoinColumn(name = "id_utilisateur")
public class Vendeur extends Utilisateur {

    public Vendeur(String nom, String email, String motDePasse) {
        super();
        this.setNom(nom);
        this.setEmail(email);
        this.setMotDePasse(motDePasse);
        this.setRole(RoleUtilisateur.VENDEUR);
    }
    
  
    @OneToMany(mappedBy = "vendeur", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Alerte> alertes;
    @OneToMany(mappedBy = "vendeur", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Produit> produits;
    
/**    @OneToMany(mappedBy = "vendeur", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Vente> ventes; **/
}
