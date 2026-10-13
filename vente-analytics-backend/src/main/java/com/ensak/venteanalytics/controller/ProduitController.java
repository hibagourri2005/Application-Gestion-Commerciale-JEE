package com.ensak.venteanalytics.controller;

import com.ensak.venteanalytics.dto.ProduitDTO;
import com.ensak.venteanalytics.service.interfaces.ProduitService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@RestController
@RequestMapping("/api/produits")
@CrossOrigin(origins = "*")
public class ProduitController {

    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping
    public List<ProduitDTO> getAllProduits() {
        return produitService.getAllProduits();
    }

    @GetMapping("/{id}")
    public ProduitDTO getProduitById(@PathVariable Long id) {
        return produitService.getProduitById(id);
    }

    @PostMapping
    public ProduitDTO createProduit(@RequestBody ProduitDTO produitDTO) {
        return produitService.createProduit(produitDTO);
    }

    @PutMapping("/{id}")
    public ProduitDTO updateProduit(@PathVariable Long id,
                                    @RequestBody ProduitDTO produitDTO) {
        return produitService.updateProduit(id, produitDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProduit(@PathVariable Long id) {
        produitService.deleteProduit(id);
    }
    
    @PostMapping("/import")
    public ResponseEntity<String> importProduits(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Le fichier est vide !");
        }

        try (Reader reader = new InputStreamReader(file.getInputStream());
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .withFirstRecordAsHeader()
                     .withIgnoreHeaderCase()
                     .withTrim())) {

            for (CSVRecord record : csvParser) {
                ProduitDTO produit = new ProduitDTO();
                produit.setNom(record.get("nom"));
                produit.setCategorieId(Long.parseLong(record.get("categorieId")));
                produit.setPrix(Double.parseDouble(record.get("prix")));
                produit.setStock(Integer.parseInt(record.get("stock")));

                produitService.createProduit(produit);
            }

            return ResponseEntity.ok("Importation réussie !");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erreur lors de l'importation : " + e.getMessage());
        }
    }
    
    
}
