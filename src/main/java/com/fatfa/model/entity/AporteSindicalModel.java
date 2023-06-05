package com.fatfa.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_aporte_sindical")
public class AporteSindicalModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_aporte", nullable = false)
	private int idAporte ;
	
	@Column(name = "nombre_aporte", length = 60)
	private String nombreAporte;

	public AporteSindicalModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AporteSindicalModel(int idAporte, String nombreAporte) {
		super();
		this.idAporte = idAporte;
		this.nombreAporte = nombreAporte;
	}

	public int getIdAporte() {
		return idAporte;
	}

	public void setIdAporte(int idAporte) {
		this.idAporte = idAporte;
	}

	public String getNombreAporte() {
		return nombreAporte;
	}

	public void setNombreAporte(String nombreAporte) {
		this.nombreAporte = nombreAporte;
	}

	@Override
	public String toString() {
		return "AporteSindicalModel [idAporte=" + idAporte + ", nombreAporte=" + nombreAporte + "]";
	}
}
