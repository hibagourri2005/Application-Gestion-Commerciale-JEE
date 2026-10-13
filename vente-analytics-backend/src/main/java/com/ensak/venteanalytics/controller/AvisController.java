package com.ensak.venteanalytics.controller;

import com.ensak.venteanalytics.dto.AvisDTO;
import com.ensak.venteanalytics.service.interfaces.AvisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avis")
public class AvisController {

    private final AvisService avisService;

    public AvisController(AvisService avisService) {
        this.avisService = avisService;
    }

    @GetMapping
    public List<AvisDTO> getAllAvis() {
        return avisService.getAllAvis();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvisDTO> getAvisById(@PathVariable Long id) {
        return ResponseEntity.ok(avisService.getAvisById(id));
    }

    @GetMapping("/produit/{produitId}")
    public List<AvisDTO> getAvisByProduit(@PathVariable Long produitId) {
        return avisService.getAvisByProduitId(produitId);
    }

    @GetMapping("/acheteur/{acheteurId}")
    public List<AvisDTO> getAvisByAcheteur(@PathVariable Long acheteurId) {
        return avisService.getAvisByAcheteurId(acheteurId);
    }

    // Création : besoin de produitId et acheteurId en paramètre de requête (ex: ?produitId=1&acheteurId=2)
    @PostMapping
    public ResponseEntity<AvisDTO> createAvis(@RequestBody AvisDTO avisDTO,
                                              @RequestParam Long produitId,
                                              @RequestParam Long acheteurId) {
        AvisDTO created = avisService.createAvis(avisDTO, produitId, acheteurId);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvisDTO> updateAvis(@PathVariable Long id,
                                              @RequestBody AvisDTO avisDTO) {
        AvisDTO updated = avisService.updateAvis(id, avisDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvis(@PathVariable Long id) {
        avisService.deleteAvis(id);
        return ResponseEntity.noContent().build();
    }
    
    
    
    
    
    
}
