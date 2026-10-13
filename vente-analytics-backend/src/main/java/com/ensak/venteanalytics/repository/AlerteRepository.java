package com.ensak.venteanalytics.repository;

import com.ensak.venteanalytics.entity.Alerte;
import com.ensak.venteanalytics.entity.Vendeur;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlerteRepository extends JpaRepository<Alerte, Long> {
	List<Alerte> findByVendeur(Vendeur vendeur);

}
