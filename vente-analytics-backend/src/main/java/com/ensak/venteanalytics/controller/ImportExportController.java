package com.ensak.venteanalytics.controller;

import com.ensak.venteanalytics.service.impl.ImportExportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/admin/import-export")
@CrossOrigin(origins = "http://localhost:3000")
public class ImportExportController {

    private final ImportExportService importExportService;

    public ImportExportController(ImportExportService importExportService) {
        this.importExportService = importExportService;
    }

    // 📥 Import CSV
    @PostMapping("/import/utilisateurs")
    public ResponseEntity<?> importerUtilisateurs(@RequestParam("file") MultipartFile file) throws Exception {

        List<String> erreurs = importExportService.importUtilisateursCSV(file);

        if (erreurs.isEmpty()) {
            return ResponseEntity.ok("Import réussi");
        }
        return ResponseEntity.badRequest().body(erreurs);
    }

    // 📤 Export CSV
    @GetMapping("/export/utilisateurs")
    public void exporterUtilisateurs(HttpServletResponse response) throws Exception {
        importExportService.exportUtilisateursCSV(response);
    }
}