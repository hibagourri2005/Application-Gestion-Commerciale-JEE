package com.ensak.venteanalytics.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvisDTO {

    private Long id;
    private int note;
    private String commentaire;
    private LocalDateTime dateCreation;

    private Long produitId;
    private Long acheteurId;
}
