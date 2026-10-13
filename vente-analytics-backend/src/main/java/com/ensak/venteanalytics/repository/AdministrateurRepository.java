package com.ensak.venteanalytics.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensak.venteanalytics.entity.Administrateur;

public interface AdministrateurRepository extends JpaRepository<Administrateur, Long> {
    Optional<Administrateur> findByEmail(String email);
}
