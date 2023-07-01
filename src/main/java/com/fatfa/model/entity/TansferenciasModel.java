package com.fatfa.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_transferencias")
public class TansferenciasModel {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_transferencia", nullable = false)
	private int idTransferencia ;
	
	@Column(name = "id_cuenta_bancaria", nullable = false)
	private int idCuentaBancaria;
	
	@OneToOne
	@JoinColumn(name = "id_empresa", nullable = false)
	private EmpresasModel empresa;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",locale = "es-PE", timezone = "America/Lima")
	@Column(name = "fecha_pago", nullable = false)
	private Date fechaPago;
	
	@Column(name = "comprobante", length = 245, nullable = false)
	private String comprobante;
	
	@OneToOne
	@JoinColumn(name = "id_estado_pago", nullable = false)
	private EstadoPagoModel estadoPago;
	
	@Column(name = "importe_pagado", columnDefinition = "DECIMAL(18,2)", nullable = false)
	private double importePagado;
	
	@Column(name = "anio_periodo_pago", length = 4, nullable = true)
	private String anioPeriodoPago;
	
	@Column(name = "mes_periodo_pago", length = 2, nullable = true)
	private String mesPeriodoPago;

	public TansferenciasModel() {
		super();
	}

	public TansferenciasModel(int idTransferencia, int idCuentaBancaria, EmpresasModel empresa, Date fechaPago,
			String comprobante, EstadoPagoModel estadoPago, double importePagado, String anioPeriodoPago,
			String mesPeriodoPago) {
		super();
		this.idTransferencia = idTransferencia;
		this.idCuentaBancaria = idCuentaBancaria;
		this.empresa = empresa;
		this.fechaPago = fechaPago;
		this.comprobante = comprobante;
		this.estadoPago = estadoPago;
		this.importePagado = importePagado;
		this.anioPeriodoPago = anioPeriodoPago;
		this.mesPeriodoPago = mesPeriodoPago;
	}

	public int getIdTransferencia() {
		return idTransferencia;
	}

	public void setIdTransferencia(int idTransferencia) {
		this.idTransferencia = idTransferencia;
	}

	public int getIdCuentaBancaria() {
		return idCuentaBancaria;
	}

	public void setIdCuentaBancaria(int idCuentaBancaria) {
		this.idCuentaBancaria = idCuentaBancaria;
	}

	public EmpresasModel getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresasModel empresa) {
		this.empresa = empresa;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getComprobante() {
		return comprobante;
	}

	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	public EstadoPagoModel getEstadoPago() {
		return estadoPago;
	}

	public void setEstadoPago(EstadoPagoModel estadoPago) {
		this.estadoPago = estadoPago;
	}

	public double getImportePagado() {
		return importePagado;
	}

	public void setImportePagado(double importePagado) {
		this.importePagado = importePagado;
	}

	public String getAnioPeriodoPago() {
		return anioPeriodoPago;
	}

	public void setAnioPeriodoPago(String anioPeriodoPago) {
		this.anioPeriodoPago = anioPeriodoPago;
	}

	public String getMesPeriodoPago() {
		return mesPeriodoPago;
	}

	public void setMesPeriodoPago(String mesPeriodoPago) {
		this.mesPeriodoPago = mesPeriodoPago;
	}

	@Override
	public String toString() {
		return "TansferenciasModel [idTransferencia=" + idTransferencia + ", idCuentaBancaria=" + idCuentaBancaria
				+ ", empresa=" + empresa + ", fechaPago=" + fechaPago + ", comprobante=" + comprobante + ", estadoPago="
				+ estadoPago + ", importePagado=" + importePagado + ", anioPeriodoPago=" + anioPeriodoPago
				+ ", mesPeriodoPago=" + mesPeriodoPago + "]";
	}	
}
