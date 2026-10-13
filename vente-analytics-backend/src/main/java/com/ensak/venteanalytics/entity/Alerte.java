package com.ensak.venteanalytics.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ensak.venteanalytics.enumtype.TypeAlerte;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "alerte")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Alerte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeAlerte typeAlerte;

    @Column(nullable = false, length = 1000)
    private String message;

    @Column(nullable = false)
    private LocalDate dateCreation;

    // Créateur = vendeur
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendeur_id", nullable = false)
    private Vendeur vendeur;
}
