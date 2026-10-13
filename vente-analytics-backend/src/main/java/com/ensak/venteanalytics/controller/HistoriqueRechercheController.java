package com.ensak.venteanalytics.controller;

import com.ensak.venteanalytics.dto.HistoriqueRechercheDTO;
import com.ensak.venteanalytics.service.interfaces.HistoriqueRechercheService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historiques-recherche")
public class HistoriqueRechercheController {

    private final HistoriqueRechercheService historiqueRechercheService;

    public HistoriqueRechercheController(HistoriqueRechercheService historiqueRechercheService) {
        this.historiqueRechercheService = historiqueRechercheService;
    }

    @GetMapping
    public List<HistoriqueRechercheDTO> getAllHistoriques() {
        return historiqueRechercheService.getAllHistoriques();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoriqueRechercheDTO> getHistoriqueById(@PathVariable Long id) {
        return ResponseEntity.ok(historiqueRechercheService.getHistoriqueById(id));
    }

    @GetMapping("/acheteur/{acheteurId}")
    public List<HistoriqueRechercheDTO> getHistoriquesByAcheteur(@PathVariable Long acheteurId) {
        return historiqueRechercheService.getHistoriqueByAcheteurId(acheteurId);
    }

    @PostMapping
    public ResponseEntity<HistoriqueRechercheDTO> createHistoriqueRecherche(
            @RequestBody HistoriqueRechercheDTO dto,
            @RequestParam Long acheteurId) {
        HistoriqueRechercheDTO created = historiqueRechercheService.createHistoriqueRecherche(dto, acheteurId);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistoriqueRecherche(@PathVariable Long id) {
        historiqueRechercheService.deleteHistoriqueRecherche(id);
        return ResponseEntity.noContent().build();
    }
}
