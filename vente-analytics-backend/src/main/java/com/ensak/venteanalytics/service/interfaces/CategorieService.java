package com.ensak.venteanalytics.service.interfaces;

import com.ensak.venteanalytics.dto.CategorieDTO;

import java.util.List;

public interface CategorieService {
    List<CategorieDTO> getAllCategories();
    CategorieDTO getCategorieById(Long id);
    CategorieDTO createCategorie(CategorieDTO dto);
    CategorieDTO updateCategorie(Long id, CategorieDTO dto);
    void deleteCategorie(Long id);
}
