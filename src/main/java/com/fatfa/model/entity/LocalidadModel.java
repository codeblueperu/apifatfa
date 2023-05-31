package com.fatfa.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_localidad")
public class LocalidadModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_localidad;
	
	@Column(length = 70, nullable = false)
	private String nombre;
	
	@Column(name = "municipio", length = 70, nullable = true)
	private String municipio;
	
	@Column(name = "codigo_postal", length = 10, nullable = true)
	private String codigoPostal ;
	
	@Column(length = 20, nullable = true)
	private String latitud;
	
	@Column(length = 20, nullable = true)
	private String longitud ;
	
	@OneToOne
	@JoinColumn(name = "id_provincia", nullable = false)
	private ProvinciasModel provincia;
	
	@OneToOne
	@JoinColumn(name = "id_partido", nullable = false)
	private PartidosModel partido;

	public LocalidadModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LocalidadModel(int id_localidad, String nombre, String municipio, String codigoPostal, String latitud,
			String longitud, ProvinciasModel provincia, PartidosModel partido) {
		super();
		this.id_localidad = id_localidad;
		this.nombre = nombre;
		this.municipio = municipio;
		this.codigoPostal = codigoPostal;
		this.latitud = latitud;
		this.longitud = longitud;
		this.provincia = provincia;
		this.partido = partido;
	}

	public int getId_localidad() {
		return id_localidad;
	}

	public void setId_localidad(int id_localidad) {
		this.id_localidad = id_localidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public ProvinciasModel getProvincia() {
		return provincia;
	}

	public void setProvincia(ProvinciasModel provincia) {
		this.provincia = provincia;
	}

	public PartidosModel getPartido() {
		return partido;
	}

	public void setPartido(PartidosModel partido) {
		this.partido = partido;
	}

	@Override
	public String toString() {
		return "LocalidadModel [id_localidad=" + id_localidad + ", nombre=" + nombre + ", municipio=" + municipio
				+ ", codigoPostal=" + codigoPostal + ", latitud=" + latitud + ", longitud=" + longitud + ", provincia="
				+ provincia + ", partido=" + partido + "]";
	}
}
