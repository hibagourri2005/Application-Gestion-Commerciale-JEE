package com.ensak.venteanalytics.repository;

import com.ensak.venteanalytics.entity.Vendeur;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VendeurRepository extends JpaRepository<Vendeur, Long> {
	Optional<Vendeur> findByEmail(String email);

	
}
