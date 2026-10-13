package com.ensak.venteanalytics.service.interfaces;

import java.util.List;
import com.ensak.venteanalytics.dto.VenteDTO;

public interface VenteService {
    List<VenteDTO> getAllVentes();
    VenteDTO getVenteById(Long id);
    VenteDTO createVente(VenteDTO venteDTO);
    VenteDTO updateVente(Long id, VenteDTO venteDTO);
    void deleteVente(Long id);
}
