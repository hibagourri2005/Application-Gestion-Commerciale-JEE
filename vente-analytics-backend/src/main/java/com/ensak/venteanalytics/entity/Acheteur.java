package com.ensak.venteanalytics.entity;

import com.ensak.venteanalytics.enumtype.RoleUtilisateur;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "acheteur")
@PrimaryKeyJoinColumn(name = "id_utilisateur")
public class Acheteur extends Utilisateur {

    public Acheteur(String nom, String email, String motDePasse) {
        super();
        this.setNom(nom);
        this.setEmail(email);
        this.setMotDePasse(motDePasse);
        this.setRole(RoleUtilisateur.ACHETEUR);
    }
}
