package com.ensak.venteanalytics.controller;

import com.ensak.venteanalytics.dto.DashboardDTO;
import com.ensak.venteanalytics.dto.StatistiqueVenteDTO;
import com.ensak.venteanalytics.service.interfaces.AnalyseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analyse")

public class AnalyseController {

    private final AnalyseService analyseService;

    public AnalyseController(AnalyseService analyseService) {
        this.analyseService = analyseService;
    }

    @GetMapping("/dashboard")
    public DashboardDTO dashboard() {
        return analyseService.genererDashboard();
    }

    @GetMapping("/statistiques")
    public List<StatistiqueVenteDTO> statistiques() {
        return analyseService.statistiquesVentesParPeriode();
    }
}
