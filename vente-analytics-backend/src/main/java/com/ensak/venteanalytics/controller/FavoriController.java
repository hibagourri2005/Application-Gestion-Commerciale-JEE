package com.ensak.venteanalytics.controller;

import com.ensak.venteanalytics.dto.FavoriDTO;
import com.ensak.venteanalytics.service.interfaces.FavoriService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favoris")
public class FavoriController {

    private final FavoriService favoriService;

    public FavoriController(FavoriService favoriService) {
        this.favoriService = favoriService;
    }

    // ➕ Ajouter un favori (ACHETEUR)
    @PostMapping
    public ResponseEntity<FavoriDTO> ajouterFavori(@RequestParam Long acheteurId,
                                                   @RequestParam Long produitId) {
        return new ResponseEntity<>(
                favoriService.ajouterFavori(acheteurId, produitId),
                HttpStatus.CREATED
        );
    }

    // 📄 Favoris d’un acheteur
    @GetMapping("/acheteur/{acheteurId}")
    public List<FavoriDTO> getFavorisAcheteur(@PathVariable Long acheteurId) {
        return favoriService.getFavorisParAcheteur(acheteurId);
    }

    // ❌ Supprimer par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerFavori(@PathVariable Long id) {
        favoriService.supprimerFavori(id);
        return ResponseEntity.noContent().build();
    }

    // ❌ Supprimer par produit
    @DeleteMapping
    public ResponseEntity<Void> supprimerFavoriParProduit(@RequestParam Long acheteurId,
                                                           @RequestParam Long produitId) {
        favoriService.supprimerFavoriParProduit(acheteurId, produitId);
        return ResponseEntity.noContent().build();
    }
}
