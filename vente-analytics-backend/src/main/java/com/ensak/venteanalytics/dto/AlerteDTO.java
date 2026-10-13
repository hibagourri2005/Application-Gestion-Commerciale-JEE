package com.ensak.venteanalytics.dto;

import com.ensak.venteanalytics.enumtype.TypeAlerte;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor

public class AlerteDTO {

    private Long id;
    private String message;
    private TypeAlerte typeAlerte;
    private LocalDate dateCreation;

    // 🔥 IMPORTANT
    private Long vendeurId;
    private String vendeurNom;
}
