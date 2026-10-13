package com.ensak.venteanalytics.service.interfaces;

import com.ensak.venteanalytics.dto.AvisDTO;

import java.util.List;

public interface AvisService {

    List<AvisDTO> getAllAvis();

    AvisDTO getAvisById(Long id);

    List<AvisDTO> getAvisByProduitId(Long produitId);

    List<AvisDTO> getAvisByAcheteurId(Long acheteurId);

    AvisDTO createAvis(AvisDTO avisDTO, Long produitId, Long acheteurId);

    AvisDTO updateAvis(Long id, AvisDTO avisDTO);

    void deleteAvis(Long id);
    
}
