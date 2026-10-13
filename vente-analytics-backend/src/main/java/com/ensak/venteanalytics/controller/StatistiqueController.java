package com.ensak.venteanalytics.controller;

import com.ensak.venteanalytics.dto.StatistiquesDTO;
import com.ensak.venteanalytics.service.interfaces.StatistiqueService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/statistiques")
@CrossOrigin(origins = "*")
public class StatistiqueController {

    private final StatistiqueService statistiqueService;

    public StatistiqueController(StatistiqueService statistiqueService) {
        this.statistiqueService = statistiqueService;
    }

    @GetMapping
    public StatistiquesDTO getDashboardAdmin() {
        return statistiqueService.getStatistiquesAdmin();
    }
}