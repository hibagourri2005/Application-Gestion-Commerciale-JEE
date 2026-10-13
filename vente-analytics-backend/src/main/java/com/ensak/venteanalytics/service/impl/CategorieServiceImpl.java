package com.ensak.venteanalytics.service.impl;

import com.ensak.venteanalytics.dto.CategorieDTO;
import com.ensak.venteanalytics.entity.Categorie;
import com.ensak.venteanalytics.entity.Produit;
import com.ensak.venteanalytics.repository.CategorieRepository;
import com.ensak.venteanalytics.service.interfaces.CategorieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategorieServiceImpl implements CategorieService {

    private final CategorieRepository categorieRepository;

    public CategorieServiceImpl(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    private CategorieDTO mapToDTO(Categorie categorie) {
        CategorieDTO dto = new CategorieDTO();
        dto.setId(categorie.getId());
        dto.setNom(categorie.getNom());
        dto.setDescription(categorie.getDescription());

        if (categorie.getProduits() != null) {
            List<CategorieDTO.ProduitInfo> produits = categorie.getProduits().stream().map(p -> {
                CategorieDTO.ProduitInfo info = new CategorieDTO.ProduitInfo();
                info.setId(p.getId());
                info.setNom(p.getNom());
                return info;
            }).collect(Collectors.toList());
            dto.setProduits(produits);
        }

        return dto;
    }

    private Categorie mapToEntity(CategorieDTO dto) {
        Categorie categorie = new Categorie();
        categorie.setNom(dto.getNom());
        categorie.setDescription(dto.getDescription());
        // Produits non gérés ici (création séparée)
        return categorie;
    }

    @Override
    public List<CategorieDTO> getAllCategories() {
        return categorieRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategorieDTO getCategorieById(Long id) {
        Categorie categorie = categorieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categorie non trouvée id=" + id));
        return mapToDTO(categorie);
    }

    @Override
    public CategorieDTO createCategorie(CategorieDTO dto) {
        Categorie categorie = mapToEntity(dto);
        categorie = categorieRepository.save(categorie);
        return mapToDTO(categorie);
    }

    @Override
    public CategorieDTO updateCategorie(Long id, CategorieDTO dto) {
        Categorie categorie = categorieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categorie non trouvée id=" + id));
        categorie.setNom(dto.getNom());
        categorie.setDescription(dto.getDescription());
        categorie = categorieRepository.save(categorie);
        return mapToDTO(categorie);
    }

    @Override
    public void deleteCategorie(Long id) {
        categorieRepository.deleteById(id);
    }
}
