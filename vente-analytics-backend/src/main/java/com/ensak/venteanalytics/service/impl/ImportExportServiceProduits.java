package com.ensak.venteanalytics.service.impl;

import com.ensak.venteanalytics.entity.Categorie;
import com.ensak.venteanalytics.entity.Produit;
import com.ensak.venteanalytics.repository.CategorieRepository;
import com.ensak.venteanalytics.repository.ProduitRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImportExportServiceProduits {

    private final ProduitRepository produitRepository;
    private final CategorieRepository categorieRepository;

    public ImportExportServiceProduits(ProduitRepository produitRepository,
                                       CategorieRepository categorieRepository) {
        this.produitRepository = produitRepository;
        this.categorieRepository = categorieRepository;
    }

    // ===========================
    // 📥 IMPORT PRODUITS CSV
    // ===========================
    public List<String> importProduitsCSV(MultipartFile file) throws IOException {
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
                if (firstLine) { firstLine = false; continue; }

                String[] data = line.split(",");
                if (data.length < 4) {
                    erreurs.add("Ligne " + ligne + " invalide");
                    continue;
                }

                String nom = data[0].trim();
                String description = data[1].trim();
                double prix;
                Long categorieId;

                try {
                    prix = Double.parseDouble(data[2].trim());
                    categorieId = Long.parseLong(data[3].trim());
                } catch (Exception e) {
                    erreurs.add("Erreur format à la ligne " + ligne);
                    continue;
                }

                Optional<Categorie> catOpt = categorieRepository.findById(categorieId);
                if (catOpt.isEmpty()) {
                    erreurs.add("Catégorie introuvable à la ligne " + ligne);
                    continue;
                }

                Produit produit = new Produit();
                produit.setNom(nom);
               
                produit.setPrix(prix);
                produit.setCategorie(catOpt.get());

                produitRepository.save(produit);
            }
        }

        return erreurs;
    }

    // ===========================
    // 📤 EXPORT PRODUITS CSV
    // ===========================
    public void exportProduitsCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=produits.csv");

        try (PrintWriter writer = response.getWriter()) {
            writer.println("ID,Nom,Description,Prix,Categorie");
            produitRepository.findAll().forEach(p -> 
                writer.println(
                        p.getId() + "," +
                        p.getNom() + "," +
                       
                        p.getPrix() + "," +
                        (p.getCategorie() != null ? p.getCategorie().getNom() : "")
                )
            );
        }
    }

    // ===========================
    // 📥 IMPORT CATEGORIES CSV
    // ===========================
    public List<String> importCategoriesCSV(MultipartFile file) throws IOException {
        List<String> erreurs = new ArrayList<>();
        if (file.isEmpty()) { erreurs.add("Fichier CSV vide"); return erreurs; }

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            boolean firstLine = true;
            int ligne = 1;

            while ((line = br.readLine()) != null) {
                ligne++;
                if (firstLine) { firstLine = false; continue; }

                String[] data = line.split(",");
                if (data.length < 2) {
                    erreurs.add("Ligne " + ligne + " invalide");
                    continue;
                }

                String nom = data[0].trim();
                String description = data[1].trim();

                if (nom.isEmpty()) { erreurs.add("Nom vide ligne " + ligne); continue; }

                Categorie categorie = new Categorie();
                categorie.setNom(nom);
                categorie.setDescription(description);

                categorieRepository.save(categorie);
            }
        }

        return erreurs;
    }

    // ===========================
    // 📤 EXPORT CATEGORIES CSV
    // ===========================
    public void exportCategoriesCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=categories.csv");

        try (PrintWriter writer = response.getWriter()) {
            writer.println("ID,Nom,Description");
            categorieRepository.findAll().forEach(c ->
                writer.println(c.getId() + "," + c.getNom() + "," + c.getDescription())
            );
        }
    }
}