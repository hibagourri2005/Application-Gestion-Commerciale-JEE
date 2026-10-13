package com.ensak.venteanalytics.service.interfaces;

import com.ensak.venteanalytics.dto.HistoriqueRechercheDTO;

import java.util.List;

public interface HistoriqueRechercheService {

    List<HistoriqueRechercheDTO> getAllHistoriques();

    HistoriqueRechercheDTO getHistoriqueById(Long id);

    List<HistoriqueRechercheDTO> getHistoriqueByAcheteurId(Long acheteurId);

    HistoriqueRechercheDTO createHistoriqueRecherche(HistoriqueRechercheDTO dto, Long acheteurId);

    void deleteHistoriqueRecherche(Long id);
}
