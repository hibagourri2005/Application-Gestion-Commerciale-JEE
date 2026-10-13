package com.ensak.venteanalytics.service.impl;

import com.ensak.venteanalytics.entity.*;
import com.ensak.venteanalytics.enumtype.RoleUtilisateur;
import com.ensak.venteanalytics.repository.UtilisateurRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImportExportService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    public ImportExportService(UtilisateurRepository utilisateurRepository,
                               PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ============================
    // 📥 IMPORT UTILISATEURS CSV
    // ============================
    public List<String> importUtilisateursCSV(MultipartFile file) throws IOException {

        List<String> erreurs = new ArrayList<>();

        if (file.isEmpty()) {
            erreurs.add("Fichier CSV vide");
            return erreurs;
        }

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            boolean firstLine = true;
            int ligne = 1;

            while ((line = br.readLine()) != null) {
                ligne++;

                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] data = line.split(",");

                if (data.length < 4) {
                    erreurs.add("Ligne " + ligne + " invalide");
                    continue;
                }

                String nom = data[0].trim();
                String email = data[1].trim();
                String motDePasse = data[2].trim();
                String roleStr = data[3].trim().toUpperCase();

                if (nom.isEmpty() || email.isEmpty() || motDePasse.isEmpty()) {
                    erreurs.add("Champs vides à la ligne " + ligne);
                    continue;
                }

                if (utilisateurRepository.existsByEmail(email)) {
                    erreurs.add("Email déjà existant : " + email);
                    continue;
                }

                RoleUtilisateur role;
                try {
                    role = RoleUtilisateur.valueOf(roleStr);
                } catch (Exception e) {
                    erreurs.add("Rôle invalide (" + roleStr + ") ligne " + ligne);
                    continue;
                }

                Utilisateur utilisateur = creerUtilisateurParRole(role);

                utilisateur.setNom(nom);
                utilisateur.setEmail(email);
                utilisateur.setRole(role);
                utilisateur.setMotDePasse(passwordEncoder.encode(motDePasse));

                utilisateurRepository.save(utilisateur);
            }
        }

        return erreurs;
    }

    // ============================
    // 📤 EXPORT UTILISATEURS CSV
    // ============================
    public void exportUtilisateursCSV(HttpServletResponse response) throws IOException {

        response.setContentType("text/csv");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition",
                "attachment; filename=utilisateurs.csv");

        try (PrintWriter writer = response.getWriter()) {
            writer.println("Nom,Email,Role");

            utilisateurRepository.findAll().forEach(u ->
                    writer.println(
                            u.getNom() + "," +
                            u.getEmail() + "," +
                            u.getRole()
                    )
            );
        }
    }

    // ============================
    // 🔧 FACTORY UTILISATEUR
    // ============================
    private Utilisateur creerUtilisateurParRole(RoleUtilisateur role) {

        return switch (role) {
            case ADMIN -> new Administrateur();
            case VENDEUR -> new Vendeur();
            case ACHETEUR -> new Acheteur();
            case ANALYSTE -> new Analyste();
        };
    }
}