package com.ensak.venteanalytics.controller;

import com.ensak.venteanalytics.dto.AlerteDTO;
import com.ensak.venteanalytics.service.interfaces.AlerteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/alertes")
public class AlerteController {

    private final AlerteService alerteService;

    public AlerteController(AlerteService alerteService) {
        this.alerteService = alerteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlerteDTO create(@RequestBody AlerteDTO dto) {
        return alerteService.create(dto);
    }

    @GetMapping
    public List<AlerteDTO> getAll() {
        return alerteService.getAll();
    }
    
    @GetMapping("/mes")
    public List<AlerteDTO> getMesAlertes() {
        return alerteService.getAlertesVendeurConnecte();
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        alerteService.delete(id);
    }
    
    @GetMapping("/{id}")
    public AlerteDTO getById(@PathVariable Long id) {
        return alerteService.getById(id);
    }
    @PostMapping("/import")
    public void importCSV(@RequestParam("file") MultipartFile file) {
        alerteService.importCSV(file);
    }
    @PutMapping("/{id}")
    public AlerteDTO update(@PathVariable Long id, @RequestBody AlerteDTO dto) {
        return alerteService.update(id, dto);
    }


}
