package com.fatfa.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_provincias")
public class ProvinciasModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_provincia", nullable = false)
	private int idProvincia;

	@Column(name = "nombre", length = 70, nullable = false)
	private String nombre;

	@Column(name = "codigo_indec")
	private String codigoIndec;

	public ProvinciasModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProvinciasModel(int idProvincia, String nombre, String codigoIndec) {
		super();
		this.idProvincia = idProvincia;
		this.nombre = nombre;
		this.codigoIndec = codigoIndec;
	}

	public int getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigoIndec() {
		return codigoIndec;
	}

	public void setCodigoIndec(String codigoIndec) {
		this.codigoIndec = codigoIndec;
	}

	@Override
	public String toString() {
		return "ProvinciasModel [idProvincia=" + idProvincia + ", nombre=" + nombre + ", codigoIndec=" + codigoIndec
				+ "]";
	}
}
