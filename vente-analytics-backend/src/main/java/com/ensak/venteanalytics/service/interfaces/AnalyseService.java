package com.ensak.venteanalytics.service.interfaces;

import com.ensak.venteanalytics.dto.DashboardDTO;
import com.ensak.venteanalytics.dto.StatistiqueVenteDTO;

import java.util.List;

public interface AnalyseService {

    DashboardDTO genererDashboard();

    List<StatistiqueVenteDTO> statistiquesVentesParPeriode();
}
