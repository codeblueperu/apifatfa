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
	
	@Column(nullable = false, columnDefinition = "BIT default 1")
	private boolean estado;

	public ZonasModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ZonasModel(int idZona, String zona, boolean estado) {
		super();
		this.idZona = idZona;
		this.zona = zona;
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

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "ZonasModel [idZona=" + idZona + ", zona=" + zona + ", estado=" + estado + "]";
	}
}
