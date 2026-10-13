package com.ensak.venteanalytics.controller;

import com.ensak.venteanalytics.service.impl.ImportExportServiceProduits;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/admin/import-export")
@CrossOrigin(origins = "http://localhost:3000")
public class ImportExportControllerProduits {

    private final ImportExportServiceProduits service;

    public ImportExportControllerProduits(ImportExportServiceProduits service) {
        this.service = service;
    }

    // 📥 Import produits
    @PostMapping("/import/produits")
    public ResponseEntity<?> importerProduits(@RequestParam("file") MultipartFile file) throws Exception {
        List<String> erreurs = service.importProduitsCSV(file);
        if (erreurs.isEmpty()) return ResponseEntity.ok("Import produits réussi");
        return ResponseEntity.badRequest().body(erreurs);
    }

    // 📤 Export produits
    @GetMapping("/export/produits")
    public void exporterProduits(HttpServletResponse response) throws Exception {
        service.exportProduitsCSV(response);
    }

    // 📥 Import catégories
    @PostMapping("/import/categories")
    public ResponseEntity<?> importerCategories(@RequestParam("file") MultipartFile file) throws Exception {
        List<String> erreurs = service.importCategoriesCSV(file);
        if (erreurs.isEmpty()) return ResponseEntity.ok("Import catégories réussi");
        return ResponseEntity.badRequest().body(erreurs);
    }

    // 📤 Export catégories
    @GetMapping("/export/categories")
    public void exporterCategories(HttpServletResponse response) throws Exception {
        service.exportCategoriesCSV(response);
    }
}