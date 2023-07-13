package com.fatfa.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_acuerdos_pagos")
public class AcuerdosPagoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_acuerdo_pago", nullable = false)
	private int idAcuerdoPago;
	
	@OneToOne
	@JoinColumn(name = "id_empresa", nullable = false)
	private EmpresasModel empresa;
	
	@NotEmpty(message = "Este campo no puede ser nulo.")
	@Size(min = 6,max = 6,message = "Este campo debe contener como minimo 6 caractes")
	@Column(name = "numero_acta", length = 6, nullable = false)
	private String numeroActa;
	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",locale = "es-PE", timezone = "America/Lima")
	@Column(name = "fecha_registro", nullable = false, columnDefinition = "DATETIME")
	private Date fechaRegistro;
	
	
	@OneToOne
	@JoinColumn(name = "id_estado_pago")
	private EstadoPagoModel estadoAcuerdo;
	
	@OneToMany( mappedBy = "acuerdo")
	private List<DetalleAcuerdoPagosCuotaModel> detalleCuotas;

	public AcuerdosPagoModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AcuerdosPagoModel(int idAcuerdoPago, EmpresasModel empresa, String numeroActa, Date fechaRegistro,
			EstadoPagoModel estadoAcuerdo, List<DetalleAcuerdoPagosCuotaModel> detalleCuotas) {
		super();
		this.idAcuerdoPago = idAcuerdoPago;
		this.empresa = empresa;
		this.numeroActa = numeroActa;
		this.fechaRegistro = fechaRegistro;
		this.estadoAcuerdo = estadoAcuerdo;
		this.detalleCuotas = detalleCuotas;
	}

	public AcuerdosPagoModel(int idAcuerdoPago) {
		super();
		this.idAcuerdoPago = idAcuerdoPago;
	}

	public int getIdAcuerdoPago() {
		return idAcuerdoPago;
	}

	public void setIdAcuerdoPago(int idAcuerdoPago) {
		this.idAcuerdoPago = idAcuerdoPago;
	}

	public EmpresasModel getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresasModel empresa) {
		this.empresa = empresa;
	}

	public String getNumeroActa() {
		return numeroActa;
	}

	public void setNumeroActa(String numeroActa) {
		this.numeroActa = numeroActa;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public EstadoPagoModel getEstadoAcuerdo() {
		return estadoAcuerdo;
	}

	public void setEstadoAcuerdo(EstadoPagoModel estadoAcuerdo) {
		this.estadoAcuerdo = estadoAcuerdo;
	}

	public List<DetalleAcuerdoPagosCuotaModel> getDetalleCuotas() {
		return detalleCuotas;
	}

	public void setDetalleCuotas(List<DetalleAcuerdoPagosCuotaModel> detalleCuotas) {
		this.detalleCuotas = detalleCuotas;
	}

	@Override
	public String toString() {
		return "AcuerdosPagoModel [idAcuerdoPago=" + idAcuerdoPago + ", empresa=" + empresa + ", numeroActa="
				+ numeroActa + ", fechaRegistro=" + fechaRegistro + ", estadoAcuerdo=" + estadoAcuerdo
				+ ", detalleCuotas=" + detalleCuotas + "]";
	}
}
