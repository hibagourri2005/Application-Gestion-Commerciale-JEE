package com.ensak.venteanalytics.service.impl;

import com.ensak.venteanalytics.dto.AlerteDTO;
import com.ensak.venteanalytics.entity.Alerte;
import com.ensak.venteanalytics.entity.Vendeur;
import com.ensak.venteanalytics.mapper.AlerteMapper;
import com.ensak.venteanalytics.repository.AlerteRepository;
import com.ensak.venteanalytics.repository.VendeurRepository;
import com.ensak.venteanalytics.service.interfaces.AlerteService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class AlerteServiceImpl implements AlerteService {

    private final AlerteRepository alerteRepository;
    private final VendeurRepository vendeurRepository;

    public AlerteServiceImpl(AlerteRepository alerteRepository,
                             VendeurRepository vendeurRepository) {
        this.alerteRepository = alerteRepository;
        this.vendeurRepository = vendeurRepository;
    }

    @Override
    public AlerteDTO create(AlerteDTO dto) {

        // 🔐 récupérer le vendeur connecté via JWT
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String email = authentication.getName();

        Vendeur vendeur = vendeurRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("VENDEUR CONNECTÉ INTROUVABLE")
                );

        // Mapper avec vendeur connecté
        Alerte alerte = AlerteMapper.toEntity(dto, vendeur);

        Alerte savedAlerte = alerteRepository.save(alerte);

        return AlerteMapper.toDTO(savedAlerte);
    }

    @Override
    public List<AlerteDTO> getAll() {
        return alerteRepository.findAll()
                .stream()
                .map(AlerteMapper::toDTO)
                .toList();
    }

    @Override
    public void delete(Long id) {
        alerteRepository.deleteById(id);
    }
    
    @Override
    public List<AlerteDTO> getAlertesVendeurConnecte() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            throw new RuntimeException("VENDEUR NON AUTHENTIFIÉ");
        }

        String email = authentication.getName();

        Vendeur vendeur = vendeurRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("VENDEUR CONNECTÉ INTROUVABLE"));

        return alerteRepository.findByVendeur(vendeur)
                .stream()
                .map(AlerteMapper::toDTO)
                .toList();
    }

    
    
    @Override
    public AlerteDTO getById(Long id) {
        Alerte alerte = alerteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alerte non trouvée"));
        return AlerteMapper.toDTO(alerte);
    }

    @Override
    public AlerteDTO update(Long id, AlerteDTO dto) {
        Alerte alerte = alerteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alerte non trouvée"));

        alerte.setMessage(dto.getMessage());
        alerte.setTypeAlerte(dto.getTypeAlerte());

        Alerte updated = alerteRepository.save(alerte);
        return AlerteMapper.toDTO(updated);
    }
    @Override
    public void importCSV(MultipartFile file) {
        // TODO: implémenter plus tard
    }

}
