package com.ensak.venteanalytics.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatistiqueVenteDTO {

    private String periode;
    private int ventesTotales;
    private double chiffreAffaires;
}
