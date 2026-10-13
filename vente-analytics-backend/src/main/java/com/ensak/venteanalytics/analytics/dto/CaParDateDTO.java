package com.ensak.venteanalytics.analytics.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CaParDateDTO {
    private LocalDate date;
    private Double chiffreAffaire;
}
