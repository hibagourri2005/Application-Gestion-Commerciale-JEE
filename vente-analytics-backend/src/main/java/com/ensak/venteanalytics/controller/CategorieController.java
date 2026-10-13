package com.ensak.venteanalytics.controller;

import com.ensak.venteanalytics.dto.CategorieDTO;
import com.ensak.venteanalytics.service.interfaces.CategorieService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {

    private final CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

  /**  @GetMapping
    public ResponseEntity<List<CategorieDTO>> getAllCategories() {
        return ResponseEntity.ok(categorieService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategorieDTO> getCategorieById(@PathVariable Long id) {
        return ResponseEntity.ok(categorieService.getCategorieById(id));
    }

    @PostMapping
    public ResponseEntity<CategorieDTO> createCategorie(@RequestBody CategorieDTO dto) {
        CategorieDTO created = categorieService.createCategorie(dto);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategorieDTO> updateCategorie(@PathVariable Long id, @RequestBody CategorieDTO dto) {
        return ResponseEntity.ok(categorieService.updateCategorie(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable Long id) {
        categorieService.deleteCategorie(id);
        return ResponseEntity.noContent().build();
    }
    
   **/ 
    
    
 // 🔓 Lecture publique
    @GetMapping("/public")
    public ResponseEntity<List<CategorieDTO>> getAllCategoriesPublic() {
        return ResponseEntity.ok(categorieService.getAllCategories());
    }

    // 🔒 Lecture complète (admin)
    @GetMapping
  //  @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CategorieDTO>> getAllCategories() {
        return ResponseEntity.ok(categorieService.getAllCategories());
    }

    @GetMapping("/{id}")
   // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategorieDTO> getCategorieById(@PathVariable Long id) {
        return ResponseEntity.ok(categorieService.getCategorieById(id));
    }

    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategorieDTO> createCategorie(@RequestBody CategorieDTO dto) {
        CategorieDTO created = categorieService.createCategorie(dto);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategorieDTO> updateCategorie(@PathVariable Long id, @RequestBody CategorieDTO dto) {
        return ResponseEntity.ok(categorieService.updateCategorie(id, dto));
    }

    @DeleteMapping("/{id}")
 //   @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCategorie(@PathVariable Long id) {
        categorieService.deleteCategorie(id);
        return ResponseEntity.noContent().build();
    }
}
