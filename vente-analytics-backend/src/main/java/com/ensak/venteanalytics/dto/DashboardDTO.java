package com.ensak.venteanalytics.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashboardDTO {

    private int totalProduits;
    private int nombreVentes;

    private double chiffreAffaires;
    private double prixMoyen;
    private double noteMoyenne;
	public DashboardDTO(int totalProduits, int nombreVentes, double chiffreAffaires, double prixMoyen,
			double noteMoyenne) {
		super();
		this.totalProduits = totalProduits;
		this.nombreVentes = nombreVentes;
		this.chiffreAffaires = chiffreAffaires;
		this.prixMoyen = prixMoyen;
		this.noteMoyenne = noteMoyenne;
	}
	public int getTotalProduits() {
		return totalProduits;
	}
	public void setTotalProduits(int totalProduits) {
		this.totalProduits = totalProduits;
	}
	public int getNombreVentes() {
		return nombreVentes;
	}
	public void setNombreVentes(int nombreVentes) {
		this.nombreVentes = nombreVentes;
	}
	public double getChiffreAffaires() {
		return chiffreAffaires;
	}
	public void setChiffreAffaires(double chiffreAffaires) {
		this.chiffreAffaires = chiffreAffaires;
	}
	public double getPrixMoyen() {
		return prixMoyen;
	}
	public void setPrixMoyen(double prixMoyen) {
		this.prixMoyen = prixMoyen;
	}
	public double getNoteMoyenne() {
		return noteMoyenne;
	}
	public void setNoteMoyenne(double noteMoyenne) {
		this.noteMoyenne = noteMoyenne;
	}
	
    
}
