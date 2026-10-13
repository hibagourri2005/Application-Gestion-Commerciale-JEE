package com.ensak.venteanalytics.service.impl;

import com.ensak.venteanalytics.dto.HistoriqueRechercheDTO;
import com.ensak.venteanalytics.entity.Acheteur;
import com.ensak.venteanalytics.entity.HistoriqueRecherche;
import com.ensak.venteanalytics.mapper.HistoriqueRechercheMapper;
import com.ensak.venteanalytics.repository.AcheteurRepository;
import com.ensak.venteanalytics.repository.HistoriqueRechercheRepository;
import com.ensak.venteanalytics.service.interfaces.HistoriqueRechercheService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoriqueRechercheServiceImpl implements HistoriqueRechercheService {

    private final HistoriqueRechercheRepository historiqueRechercheRepository;
    private final AcheteurRepository acheteurRepository;

    public HistoriqueRechercheServiceImpl(HistoriqueRechercheRepository historiqueRechercheRepository,
                                          AcheteurRepository acheteurRepository) {
        this.historiqueRechercheRepository = historiqueRechercheRepository;
        this.acheteurRepository = acheteurRepository;
    }

    @Override
    public List<HistoriqueRechercheDTO> getAllHistoriques() {
        return historiqueRechercheRepository.findAll().stream()
                .map(HistoriqueRechercheMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HistoriqueRechercheDTO getHistoriqueById(Long id) {
        HistoriqueRecherche entity = historiqueRechercheRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Historique non trouvé"));
        return HistoriqueRechercheMapper.toDTO(entity);
    }

    @Override
    public List<HistoriqueRechercheDTO> getHistoriqueByAcheteurId(Long acheteurId) {
        return historiqueRechercheRepository.findByAcheteurIdOrderByDateRechercheDesc(acheteurId).stream()
                .map(HistoriqueRechercheMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HistoriqueRechercheDTO createHistoriqueRecherche(HistoriqueRechercheDTO dto, Long acheteurId) {
        Acheteur acheteur = acheteurRepository.findById(acheteurId)
                .orElseThrow(() -> new RuntimeException("Acheteur non trouvé"));

        HistoriqueRecherche entity = HistoriqueRechercheMapper.toEntity(dto, acheteur);

        // S'il n'y a pas de date, on met la date actuelle
        if (entity.getDateRecherche() == null) {
            entity.setDateRecherche(LocalDateTime.now());
        }

        HistoriqueRecherche saved = historiqueRechercheRepository.save(entity);
        return HistoriqueRechercheMapper.toDTO(saved);
    }

    @Override
    public void deleteHistoriqueRecherche(Long id) {
        historiqueRechercheRepository.deleteById(id);
    }
}
