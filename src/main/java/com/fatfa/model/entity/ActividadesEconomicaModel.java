package com.fatfa.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_actividades_economicas")
public class ActividadesEconomicaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "codigo_afip", length = 50, nullable = false)
	private String codigoafip;
	
	@Column(name = "nombre", length = 50, nullable = false)
	private String nombre;
	
	@Column(name = "descripcion", length = 50, nullable = false)
	private String descripcion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigoafip() {
		return codigoafip;
	}

	public void setCodigoafip(String codigoafip) {
		this.codigoafip = codigoafip;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "ActividadesEconomicaModel [id=" + id + ", codigoafip=" + codigoafip + ", nombre=" + nombre
				+ ", descripcion=" + descripcion + "]";
	}
	
	
}
