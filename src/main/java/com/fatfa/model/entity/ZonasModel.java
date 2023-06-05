package com.fatfa.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_zonas")
public class ZonasModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_zona")
	private int idZona ;
	
	@Column(name = "zona", length = 50, nullable = false)
	private String zona;
	
	@Column(name = "aporte_zona",nullable =  false , columnDefinition = "DECIMAL(18,2)")
	private double aporteZona;
	
	@Column(nullable = false, columnDefinition = "BIT default 1")
	private boolean estado;

	public ZonasModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ZonasModel(int idZona, String zona, double aporteZona, boolean estado) {
		super();
		this.idZona = idZona;
		this.zona = zona;
		this.aporteZona = aporteZona;
		this.estado = estado;
	}

	public int getIdZona() {
		return idZona;
	}

	public void setIdZona(int idZona) {
		this.idZona = idZona;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public double getAporteZona() {
		return aporteZona;
	}

	public void setAporteZona(double aporteZona) {
		this.aporteZona = aporteZona;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "ZonasModel [idZona=" + idZona + ", zona=" + zona + ", aporteZona=" + aporteZona + ", estado=" + estado
				+ "]";
	}
}
