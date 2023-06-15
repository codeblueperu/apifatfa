package com.fatfa.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

	@Column(name = "nombre_sindicato", length=50, nullable = false)
	private String nombreSindicato;

	@Column(name = "telefono_sindicato",length = 25, nullable = false)
	private String telefonoSindicato;

	@Column(name = "domicilio_sindicato", length = 100, nullable = false)
	private String domicilio;
	
	@Column(name = "nombres_responsable", length = 50, nullable = false)
	private String nombreResponsable;
	
	@Column(name = "apellidos_responsable", length = 70, nullable = false)
	private String apellidoResponsable;
	
	@Column(name = "telefono_responsable", length = 25, nullable = false)
	private String telefonoResponsable;
	
	@Column(name = "correo_responsable", length = 50, nullable = false)
	private String correoResponsable;
	
	@Column(name = "avatar_sindicato", length = 50, nullable = false)
	private String avatar;
	@Column(name = "estado", nullable = false, columnDefinition = "BIT default 0")
	private boolean estado;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_localidad")
	private LocalidadModel idLocalidad;

	public SindicatosModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "SindicatosModel [idSindicato=" + idSindicato + ", estadoSindicato=" + nombreSindicato
				+ ", telefonoSindicato=" + telefonoSindicato + ", domicilio=" + domicilio + ", nombreResponsable="
				+ nombreResponsable + ", apellidoResponsable=" + apellidoResponsable + ", telefonoResponsable="
				+ telefonoResponsable + ", correoResponsable=" + correoResponsable + ", avatar=" + avatar + ", estado="
				+ estado + ", idLocalidad=" + idLocalidad + "]";
	}

	public int getIdSindicato() {
		return idSindicato;
	}

	public void setIdSindicato(int idSindicato) {
		this.idSindicato = idSindicato;
	}

	
	public String getNombreSindicato() {
		return nombreSindicato;
	}

	public void setNombreSindicato(String nombreSindicato) {
		this.nombreSindicato = nombreSindicato;
	}

	public String getTelefonoSindicato() {
		return telefonoSindicato;
	}

	public void setTelefonoSindicato(String telefonoSindicato) {
		this.telefonoSindicato = telefonoSindicato;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getNombreResponsable() {
		return nombreResponsable;
	}

	public void setNombreResponsable(String nombreResponsable) {
		this.nombreResponsable = nombreResponsable;
	}

	public String getApellidoResponsable() {
		return apellidoResponsable;
	}

	public void setApellidoResponsable(String apellidoResponsable) {
		this.apellidoResponsable = apellidoResponsable;
	}

	public String getTelefonoResponsable() {
		return telefonoResponsable;
	}

	public void setTelefonoResponsable(String telefonoResponsable) {
		this.telefonoResponsable = telefonoResponsable;
	}

	public String getCorreoResponsable() {
		return correoResponsable;
	}

	public void setCorreoResponsable(String correoResponsable) {
		this.correoResponsable = correoResponsable;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public LocalidadModel getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(LocalidadModel idLocalidad) {
		this.idLocalidad = idLocalidad;
	}
	

	
}
