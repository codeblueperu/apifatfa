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
@Table(name = "tb_domicilio")
public class DomicilioModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_domicilio", nullable = false)
	private int idDomicilio ;
	
	@Column(length = 50, nullable = true)
	private String calle;
	
	@Column(length = 50, nullable = true)
	private String numero;
	
	@Column(length = 5, nullable = true)
	private String piso ;
	
	@Column(length = 70, nullable = true)
	private String departamento;
	
	@Column(name="codigo_postal",length = 50, nullable = true)
	private String codigoPostal;
	
	@OneToOne
	@JoinColumn(name = "id_provincia")
	private ProvinciasModel provincia;
	
	@OneToOne
	@JoinColumn(name = "id_partido")
	private PartidosModel partido;
	
	
	@OneToOne
	@JoinColumn(name = "id_localidad")
	private LocalidadModel localidad;


	public DomicilioModel() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DomicilioModel(int idDomicilio, String calle, String numero, String piso, String departamento,
			String codigoPostal, ProvinciasModel provincia, PartidosModel partido, LocalidadModel localidad) {
		super();
		this.idDomicilio = idDomicilio;
		this.calle = calle;
		this.numero = numero;
		this.piso = piso;
		this.departamento = departamento;
		this.codigoPostal = codigoPostal;
		this.provincia = provincia;
		this.partido = partido;
		this.localidad = localidad;
	}


	public int getIdDomicilio() {
		return idDomicilio;
	}


	public void setIdDomicilio(int idDomicilio) {
		this.idDomicilio = idDomicilio;
	}


	public String getCalle() {
		return calle;
	}


	public void setCalle(String calle) {
		this.calle = calle;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getPiso() {
		return piso;
	}


	public void setPiso(String piso) {
		this.piso = piso;
	}


	public String getDepartamento() {
		return departamento;
	}


	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}


	public String getCodigoPostal() {
		return codigoPostal;
	}


	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
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


	public LocalidadModel getLocalidad() {
		return localidad;
	}


	public void setLocalidad(LocalidadModel localidad) {
		this.localidad = localidad;
	}


	@Override
	public String toString() {
		return "DomicilioModel [idDomicilio=" + idDomicilio + ", calle=" + calle + ", numero=" + numero + ", piso="
				+ piso + ", departamento=" + departamento + ", codigoPostal=" + codigoPostal + ", provincia="
				+ provincia + ", partido=" + partido + ", localidad=" + localidad + "]";
	}
}
