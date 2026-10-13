package com.ensak.venteanalytics.service.impl;

import com.ensak.venteanalytics.dto.UtilisateurDTO;
import com.ensak.venteanalytics.entity.*;
import com.ensak.venteanalytics.mapper.UtilisateurMapper;
import com.ensak.venteanalytics.repository.UtilisateurRepository;
import com.ensak.venteanalytics.service.interfaces.UtilisateurService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository,
                                  PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Utilisateur creerUtilisateur(UtilisateurDTO dto) {

        Utilisateur user;

        switch (dto.getRole()) {
            case ADMIN -> user = new Administrateur();
            case VENDEUR -> user = new Vendeur();
            case ACHETEUR -> user = new Acheteur();
            case ANALYSTE -> user = new Analyste();
            default -> throw new RuntimeException("Rôle invalide");
        }

        user.setNom(dto.getNom());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        user.setMotDePasse(passwordEncoder.encode(dto.getMotDePasse()));

        return utilisateurRepository.save(user);
    }

    @Override
    public Utilisateur modifierUtilisateur(Long id, UtilisateurDTO dto) {
        Utilisateur user = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        user.setNom(dto.getNom());
        user.setEmail(dto.getEmail());

        return utilisateurRepository.save(user);
    }

    @Override
    public void supprimerUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }

    @Override
    public List<UtilisateurDTO> listerUtilisateurs() {
        return utilisateurRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public UtilisateurDTO trouverParId(Long id) {
        return utilisateurRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
    }

    @Override
    public Utilisateur findByEmail(String email) {
        return utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
    }

    // 🔁 Mapper
    private UtilisateurDTO toDTO(Utilisateur u) {
        UtilisateurDTO dto = new UtilisateurDTO();
        dto.setId(u.getId());
        dto.setNom(u.getNom());
        dto.setEmail(u.getEmail());
        dto.setRole(u.getRole());
        return dto;
    }
    
    @Override
    public UtilisateurDTO getUtilisateurByEmail(String email) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        return UtilisateurMapper.toDTO(utilisateur);
    }
}
