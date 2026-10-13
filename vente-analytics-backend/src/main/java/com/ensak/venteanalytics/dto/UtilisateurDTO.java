package com.ensak.venteanalytics.dto;

import com.ensak.venteanalytics.enumtype.RoleUtilisateur;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtilisateurDTO {

    private Long id;

    @NotBlank
    private String nom;

    @Email
    private String email;

    private String motDePasse; // utilisé UNIQUEMENT à la création

    private RoleUtilisateur role;
}
