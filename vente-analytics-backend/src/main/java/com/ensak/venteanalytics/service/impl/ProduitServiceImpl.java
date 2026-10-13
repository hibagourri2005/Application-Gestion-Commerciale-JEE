package com.ensak.venteanalytics.service.impl;

import com.ensak.venteanalytics.dto.ProduitDTO;
import com.ensak.venteanalytics.entity.Categorie;
import com.ensak.venteanalytics.entity.Produit;
import com.ensak.venteanalytics.mapper.ProduitMapper;
import com.ensak.venteanalytics.repository.CategorieRepository;
import com.ensak.venteanalytics.repository.ProduitRepository;
import com.ensak.venteanalytics.service.interfaces.ProduitService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;
    private final CategorieRepository categorieRepository;

    public ProduitServiceImpl(ProduitRepository produitRepository, CategorieRepository categorieRepository) {
        this.produitRepository = produitRepository;
        this.categorieRepository = categorieRepository;
    }

    @Override
    public List<ProduitDTO> getAllProduits() {
        return produitRepository.findAll()
                .stream()
                .map(ProduitMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProduitDTO getProduitById(Long id) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));
        return ProduitMapper.toDTO(produit);
    }

    @Override
    public ProduitDTO createProduit(ProduitDTO produitDTO) {
        Produit produit = ProduitMapper.toEntity(produitDTO);

        // Chargement catégorie réelle
        if (produitDTO.getCategorieId() != null) {
            Categorie categorie = categorieRepository.findById(produitDTO.getCategorieId())
                    .orElseThrow(() -> new RuntimeException("Catégorie introuvable"));
            produit.setCategorie(categorie);
        } else {
            produit.setCategorie(null);
        }

        produit = produitRepository.save(produit);
        return ProduitMapper.toDTO(produit);
    }

    @Override
    public ProduitDTO updateProduit(Long id, ProduitDTO produitDTO) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));

        produit.setNom(produitDTO.getNom());

        if (produitDTO.getCategorieId() != null) {
            Categorie categorie = categorieRepository.findById(produitDTO.getCategorieId())
                    .orElseThrow(() -> new RuntimeException("Catégorie introuvable"));
            produit.setCategorie(categorie);
        } else {
            produit.setCategorie(null);
        }

        produit.setPrix(produitDTO.getPrix());
        produit.setStock(produitDTO.getStock());

        produit = produitRepository.save(produit);
        return ProduitMapper.toDTO(produit);
    }

    @Override
    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }
    
    private ProduitDTO toDTO(Produit p) {

        ProduitDTO dto = new ProduitDTO();
        dto.setId(p.getId());
        dto.setNom(p.getNom());
        dto.setPrix(p.getPrix());
        dto.setStock(p.getStock());

        // ✅ PARTIE CRITIQUE
        if (p.getCategorie() != null) {
            dto.setCategorieId(p.getCategorie().getId());
            dto.setCategorieNom(p.getCategorie().getNom());
        }

        return dto;
    }
}
