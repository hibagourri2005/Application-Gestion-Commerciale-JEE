package com.ensak.venteanalytics.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensak.venteanalytics.dto.VenteDTO;
import com.ensak.venteanalytics.entity.Vente;
import com.ensak.venteanalytics.entity.Produit;
import com.ensak.venteanalytics.entity.Vendeur;
import com.ensak.venteanalytics.repository.VenteRepository;
import com.ensak.venteanalytics.repository.ProduitRepository;
import com.ensak.venteanalytics.repository.VendeurRepository;
import com.ensak.venteanalytics.service.interfaces.VenteService;

@Service
@Transactional
public class VenteServiceImpl implements VenteService {

    private final VenteRepository venteRepository;
    private final ProduitRepository produitRepository;
    private final VendeurRepository vendeurRepository;

    public VenteServiceImpl(VenteRepository venteRepository, ProduitRepository produitRepository, VendeurRepository vendeurRepository) {
        this.venteRepository = venteRepository;
        this.produitRepository = produitRepository;
        this.vendeurRepository = vendeurRepository;
    }

    @Override
    public List<VenteDTO> getAllVentes() {
        return venteRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public VenteDTO getVenteById(Long id) {
        Vente vente = venteRepository.findById(id).orElseThrow(() -> new RuntimeException("Vente non trouvée"));
        return mapToDTO(vente);
    }

    @Override
    public VenteDTO createVente(VenteDTO dto) {

        Produit produit = produitRepository.findById(dto.getProduitId())
            .orElseThrow(() -> new RuntimeException("Produit non trouvé"));

        Vendeur vendeur = vendeurRepository.findById(dto.getVendeurId())
            .orElseThrow(() -> new RuntimeException("Vendeur non trouvé"));

        // Vérification stock
        if (produit.getStock() < dto.getQuantite()) {
            throw new RuntimeException("Stock insuffisant");
        }

        Vente vente = new Vente();
        vente.setProduit(produit);
        vente.setVendeur(vendeur);
        vente.setQuantite(dto.getQuantite());

        // Date par défaut
        vente.setDateVente(
            dto.getDateVente() != null ? dto.getDateVente() : java.time.LocalDate.now()
        );

        // Calcul prix total
        double prixTotal = produit.getPrix() * dto.getQuantite();
        vente.setPrixTotal(prixTotal);

        // Mise à jour stock
        produit.setStock(produit.getStock() - dto.getQuantite());
        produitRepository.save(produit);

        Vente saved = venteRepository.save(vente);
        return mapToDTO(saved);
    }


    @Override
    public VenteDTO updateVente(Long id, VenteDTO dto) {
        Vente vente = venteRepository.findById(id).orElseThrow(() -> new RuntimeException("Vente non trouvée"));

        vente.setQuantite(dto.getQuantite());
        vente.setPrixTotal(dto.getPrixTotal());
        vente.setDateVente(dto.getDateVente());

        if (dto.getProduitId() != null) {
            Produit produit = produitRepository.findById(dto.getProduitId()).orElseThrow(() -> new RuntimeException("Produit non trouvé"));
            vente.setProduit(produit);
        }
        if (dto.getVendeurId() != null) {
            Vendeur vendeur = vendeurRepository.findById(dto.getVendeurId()).orElseThrow(() -> new RuntimeException("Vendeur non trouvé"));
            vente.setVendeur(vendeur);
        }

        Vente updated = venteRepository.save(vente);
        return mapToDTO(updated);
    }

    @Override
    public void deleteVente(Long id) {
        venteRepository.deleteById(id);
    }

    // Méthode utilitaire pour conversion entity->DTO
    private VenteDTO mapToDTO(Vente vente) {
        VenteDTO dto = new VenteDTO();
        dto.setId(vente.getId());
        dto.setQuantite(vente.getQuantite());
        dto.setPrixTotal(vente.getPrixTotal());
        dto.setDateVente(vente.getDateVente());
        if (vente.getProduit() != null) {
            dto.setProduitId(vente.getProduit().getId());
            dto.setProduitNom(vente.getProduit().getNom());
        }
        if (vente.getVendeur() != null) {
            dto.setVendeurId(vente.getVendeur().getId());
            dto.setVendeurNom(vente.getVendeur().getNom());
        }
        return dto;
    }
}
