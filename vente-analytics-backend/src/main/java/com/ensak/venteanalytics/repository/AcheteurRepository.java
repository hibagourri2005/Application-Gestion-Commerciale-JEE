package com.ensak.venteanalytics.repository;

import com.ensak.venteanalytics.entity.Acheteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcheteurRepository extends JpaRepository<Acheteur, Long> {
}
