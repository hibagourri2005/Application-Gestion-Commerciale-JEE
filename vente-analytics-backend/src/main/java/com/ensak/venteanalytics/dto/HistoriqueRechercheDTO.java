package com.ensak.venteanalytics.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoriqueRechercheDTO {

    private Long id;
    private String termeRecherche;
    private LocalDateTime dateRecherche;
    private Long acheteurId;
}
