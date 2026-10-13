package com.ensak.venteanalytics.controller;

import com.ensak.venteanalytics.dto.UtilisateurDTO;
import com.ensak.venteanalytics.entity.Utilisateur;
import com.ensak.venteanalytics.service.interfaces.UtilisateurService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@CrossOrigin(origins = "*")
public class UtilisateurController {

    private final UtilisateurService service;

    public UtilisateurController(UtilisateurService service) {
        this.service = service;
    }

    @GetMapping("/public")
    public ResponseEntity<List<UtilisateurDTO>> publicList() {
        return ResponseEntity.ok(service.listerUtilisateurs());
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<UtilisateurDTO>> list() {
        return ResponseEntity.ok(service.listerUtilisateurs());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> create(@RequestBody UtilisateurDTO dto) {
        try {
            Utilisateur u = service.creerUtilisateur(dto);
            return ResponseEntity.status(201).body(service.trouverParId(u.getId()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UtilisateurDTO dto) {
        try {
            Utilisateur updated = service.modifierUtilisateur(id, dto);
            return ResponseEntity.ok(service.trouverParId(updated.getId()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.supprimerUtilisateur(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}