package com.ensak.venteanalytics.entity;

import com.ensak.venteanalytics.enumtype.RoleUtilisateur;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "administrateur")
@PrimaryKeyJoinColumn(name = "id_utilisateur")
public class Administrateur extends Utilisateur {

    public Administrateur(String nom, String email, String motDePasse) {
        super();
        this.setNom(nom);
        this.setEmail(email);
        this.setMotDePasse(motDePasse);
        this.setRole(RoleUtilisateur.ADMIN);
    }
}
