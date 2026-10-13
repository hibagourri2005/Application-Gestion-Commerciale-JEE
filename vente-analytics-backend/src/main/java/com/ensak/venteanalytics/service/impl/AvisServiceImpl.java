package com.ensak.venteanalytics.service.impl;

import com.ensak.venteanalytics.dto.AvisDTO;
import com.ensak.venteanalytics.entity.Avis;
import com.ensak.venteanalytics.entity.Produit;
import com.ensak.venteanalytics.entity.Acheteur;
import com.ensak.venteanalytics.mapper.AvisMapper;
import com.ensak.venteanalytics.repository.AvisRepository;
import com.ensak.venteanalytics.repository.ProduitRepository;
import com.ensak.venteanalytics.repository.AcheteurRepository;
import com.ensak.venteanalytics.service.interfaces.AvisService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvisServiceImpl implements AvisService {

    private final AvisRepository avisRepository;
    private final ProduitRepository produitRepository;
    private final AcheteurRepository acheteurRepository;

    public AvisServiceImpl(AvisRepository avisRepository, ProduitRepository produitRepository, AcheteurRepository acheteurRepository) {
        this.avisRepository = avisRepository;
        this.produitRepository = produitRepository;
        this.acheteurRepository = acheteurRepository;
    }

    @Override
    public List<AvisDTO> getAllAvis() {
        return avisRepository.findAll().stream()
                .map(AvisMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AvisDTO getAvisById(Long id) {
        Avis avis = avisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avis non trouvé"));
        return AvisMapper.toDTO(avis);
    }

    @Override
    public List<AvisDTO> getAvisByProduitId(Long produitId) {
        return avisRepository.findByProduitId(produitId).stream()
                .map(AvisMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AvisDTO> getAvisByAcheteurId(Long acheteurId) {
        return avisRepository.findByAcheteurId(acheteurId).stream()
                .map(AvisMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AvisDTO createAvis(AvisDTO avisDTO, Long produitId, Long acheteurId) {
        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
        Acheteur acheteur = acheteurRepository.findById(acheteurId)
                .orElseThrow(() -> new RuntimeException("Acheteur non trouvé"));

        Avis avis = AvisMapper.toEntity(avisDTO, produit, acheteur);
        avis.setDateCreation(LocalDateTime.now());

        Avis saved = avisRepository.save(avis);
        return AvisMapper.toDTO(saved);
    }

    @Override
    public AvisDTO updateAvis(Long id, AvisDTO avisDTO) {
        Avis avis = avisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avis non trouvé"));

        avis.setNote(avisDTO.getNote());
        avis.setCommentaire(avisDTO.getCommentaire());

        Avis updated = avisRepository.save(avis);
        return AvisMapper.toDTO(updated);
    }

    @Override
    public void deleteAvis(Long id) {
        avisRepository.deleteById(id);
    }
}
