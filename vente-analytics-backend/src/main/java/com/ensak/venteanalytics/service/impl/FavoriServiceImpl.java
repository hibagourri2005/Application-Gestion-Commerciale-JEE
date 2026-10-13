package com.ensak.venteanalytics.service.impl;

import com.ensak.venteanalytics.dto.FavoriDTO;
import com.ensak.venteanalytics.entity.Acheteur;
import com.ensak.venteanalytics.entity.Favori;
import com.ensak.venteanalytics.entity.Produit;
import com.ensak.venteanalytics.mapper.FavoriMapper;
import com.ensak.venteanalytics.repository.AcheteurRepository;
import com.ensak.venteanalytics.repository.FavoriRepository;
import com.ensak.venteanalytics.repository.ProduitRepository;
import com.ensak.venteanalytics.service.interfaces.FavoriService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriServiceImpl implements FavoriService {

    private final FavoriRepository favoriRepository;
    private final AcheteurRepository acheteurRepository;
    private final ProduitRepository produitRepository;

    public FavoriServiceImpl(FavoriRepository favoriRepository,
                             AcheteurRepository acheteurRepository,
                             ProduitRepository produitRepository) {
        this.favoriRepository = favoriRepository;
        this.acheteurRepository = acheteurRepository;
        this.produitRepository = produitRepository;
    }

    @Override
    public FavoriDTO ajouterFavori(Long acheteurId, Long produitId) {

        // empêcher doublon
        favoriRepository.findByAcheteurIdAndProduitId(acheteurId, produitId)
                .ifPresent(f -> {
                    throw new RuntimeException("Produit déjà en favori");
                });

        Acheteur acheteur = acheteurRepository.findById(acheteurId)
                .orElseThrow(() -> new RuntimeException("Acheteur introuvable"));

        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));

        Favori favori = FavoriMapper.toEntity(acheteur, produit);
        return FavoriMapper.toDTO(favoriRepository.save(favori));
    }

    @Override
    public void supprimerFavori(Long favoriId) {
        favoriRepository.deleteById(favoriId);
    }

    @Override
    public void supprimerFavoriParProduit(Long acheteurId, Long produitId) {
        Favori favori = favoriRepository.findByAcheteurIdAndProduitId(acheteurId, produitId)
                .orElseThrow(() -> new RuntimeException("Favori non trouvé"));

        favoriRepository.delete(favori);
    }

    @Override
    public List<FavoriDTO> getFavorisParAcheteur(Long acheteurId) {
        return favoriRepository.findByAcheteurId(acheteurId)
                .stream()
                .map(FavoriMapper::toDTO)
                .collect(Collectors.toList());
    }
}
