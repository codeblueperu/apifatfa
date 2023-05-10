package com.fatfa.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_tipo_juridiccion")
public class TipoJuridiccionModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_juridiccion", nullable = false)
	private int idJuridiccion;
	
	@Column(length = 70, nullable = false)
	private String juridiccion;

	public TipoJuridiccionModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoJuridiccionModel(int idJuridiccion, String juridiccion) {
		super();
		this.idJuridiccion = idJuridiccion;
		this.juridiccion = juridiccion;
	}

	public int getIdJuridiccion() {
		return idJuridiccion;
	}

	public void setIdJuridiccion(int idJuridiccion) {
		this.idJuridiccion = idJuridiccion;
	}

	public String getJuridiccion() {
		return juridiccion;
	}

	public void setJuridiccion(String juridiccion) {
		this.juridiccion = juridiccion;
	}

	@Override
	public String toString() {
		return "TipoJuridiccionModel [idJuridiccion=" + idJuridiccion + ", juridiccion=" + juridiccion + "]";
	}
}
