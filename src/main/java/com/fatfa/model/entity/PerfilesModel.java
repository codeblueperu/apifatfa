package com.fatfa.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_perfiles")
public class PerfilesModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_perfil", nullable = false)
	private int idPerfil;
	
	@Column(name = "descripcion", length = 45, nullable = false, unique = true)
	private String descripcion;
	
	@Column(name = "estado", nullable = false, columnDefinition = "BIT default true")
	private boolean estado;

	public PerfilesModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PerfilesModel(int idPerfil, String descripcion, boolean estado) {
		super();
		this.idPerfil = idPerfil;
		this.descripcion = descripcion;
		this.estado = estado;
	}

	public int getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "PerfilesModel [idPerfil=" + idPerfil + ", descripcion=" + descripcion + ", estado=" + estado + "]";
	}
}
