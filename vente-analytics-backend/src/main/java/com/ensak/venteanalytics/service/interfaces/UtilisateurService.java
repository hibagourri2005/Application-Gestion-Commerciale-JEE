package com.ensak.venteanalytics.service.interfaces;

import com.ensak.venteanalytics.dto.UtilisateurDTO;
import com.ensak.venteanalytics.entity.Utilisateur;

import java.util.List;

public interface UtilisateurService {

    Utilisateur creerUtilisateur(UtilisateurDTO dto);

    Utilisateur modifierUtilisateur(Long id, UtilisateurDTO dto);

    void supprimerUtilisateur(Long id);

    List<UtilisateurDTO> listerUtilisateurs();

    UtilisateurDTO trouverParId(Long id);

    Utilisateur findByEmail(String email);
    UtilisateurDTO getUtilisateurByEmail(String email);

}
