package com.ensak.venteanalytics.service.interfaces;


import com.ensak.venteanalytics.dto.ProduitDTO;

import java.util.List;

public interface ProduitService {

    List<ProduitDTO> getAllProduits();

    ProduitDTO getProduitById(Long id);

    ProduitDTO createProduit(ProduitDTO produitDTO);

    ProduitDTO updateProduit(Long id, ProduitDTO produitDTO);

    void deleteProduit(Long id);
}
