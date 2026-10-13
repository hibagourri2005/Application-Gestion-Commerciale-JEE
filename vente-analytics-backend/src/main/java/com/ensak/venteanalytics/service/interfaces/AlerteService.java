package com.ensak.venteanalytics.service.interfaces;

import com.ensak.venteanalytics.dto.AlerteDTO;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface AlerteService {

    AlerteDTO create(AlerteDTO dto);
    List<AlerteDTO> getAll();
    void delete(Long id);
    List<AlerteDTO> getAlertesVendeurConnecte();
    AlerteDTO getById(Long id);
    AlerteDTO update(Long id, AlerteDTO dto);
    void importCSV(MultipartFile file);

}
