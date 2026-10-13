package com.ensak.venteanalytics.analytics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RechercheTopDTO {
    private String terme;
    private Long total;
}
