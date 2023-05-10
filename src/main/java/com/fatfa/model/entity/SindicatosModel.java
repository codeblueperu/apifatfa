package com.fatfa.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_sindicatos")
public class SindicatosModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_sindicato")
	private int idSindicato;

	@Column(length = 70, nullable = false)
	private String nombre;

	@Column(nullable = false, columnDefinition = "BIT default 1")
	private boolean estado;

	@Column(length = 30, nullable = false)
	private String telefono;

	@Column(length = 70, nullable = false)
	private String region;

	@OneToOne()
	@JoinColumn(name = "id_juridiccion")
	private TipoJuridiccionModel juridiccion;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_domicilio")
	private DomicilioModel domicilio;

	public SindicatosModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SindicatosModel(int idSindicato, String nombre, boolean estado, String telefono, String region,
			TipoJuridiccionModel juridiccion, DomicilioModel domicilio) {
		super();
		this.idSindicato = idSindicato;
		this.nombre = nombre;
		this.estado = estado;
		this.telefono = telefono;
		this.region = region;
		this.juridiccion = juridiccion;
		this.domicilio = domicilio;
	}

	public int getIdSindicato() {
		return idSindicato;
	}

	public void setIdSindicato(int idSindicato) {
		this.idSindicato = idSindicato;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public TipoJuridiccionModel getJuridiccion() {
		return juridiccion;
	}

	public void setJuridiccion(TipoJuridiccionModel juridiccion) {
		this.juridiccion = juridiccion;
	}

	public DomicilioModel getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(DomicilioModel domicilio) {
		this.domicilio = domicilio;
	}

	@Override
	public String toString() {
		return "SindicatosModel [idSindicato=" + idSindicato + ", nombre=" + nombre + ", estado=" + estado
				+ ", telefono=" + telefono + ", region=" + region + ", juridiccion=" + juridiccion + ", domicilio="
				+ domicilio + "]";
	}
}
