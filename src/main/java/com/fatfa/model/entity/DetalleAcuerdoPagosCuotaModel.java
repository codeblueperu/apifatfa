package com.fatfa.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_detalle_acuerdo_pagos_cuotas")
public class DetalleAcuerdoPagosCuotaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_detalle_cuota", nullable = false)
	private int idDetalleCuota;
	
	@Column(name = "num_cuota_sindical", nullable = false, columnDefinition = "DECIMAL(18,2)")
	private double nCuotaSindical;
	
	@Column(name = "subtotal", nullable = false, columnDefinition = "DECIMAL(18,2)")
	private double subTotal;
	
	@Column(name = "intereses", nullable = false, columnDefinition = "DECIMAL(18,2)")
	private double intereses;
	
	@Column(name = "importe_total", nullable = false, columnDefinition = "DECIMAL(18,2)")
	private double importeTotal;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",locale = "es-PE", timezone = "America/Lima")
	@Column(name = "fecha_vencimiento", nullable = false, columnDefinition = "DATETIME")
	private Date fechaVencimiento;
	
	@Column(name = "estado_pago", nullable = false, columnDefinition = "TINYINT default 0")
	private boolean estadoPago;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_acuerdo_pago", nullable = false, updatable = false)
	private AcuerdosPagoModel acuerdo;

	public DetalleAcuerdoPagosCuotaModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DetalleAcuerdoPagosCuotaModel(int idDetalleCuota, double nCuotaSindical, double subTotal, double intereses,
			double importeTotal, Date fechaVencimiento, boolean estadoPago, AcuerdosPagoModel acuerdo) {
		super();
		this.idDetalleCuota = idDetalleCuota;
		this.nCuotaSindical = nCuotaSindical;
		this.subTotal = subTotal;
		this.intereses = intereses;
		this.importeTotal = importeTotal;
		this.fechaVencimiento = fechaVencimiento;
		this.estadoPago = estadoPago;
		this.acuerdo = acuerdo;
	}

	public int getIdDetalleCuota() {
		return idDetalleCuota;
	}

	public void setIdDetalleCuota(int idDetalleCuota) {
		this.idDetalleCuota = idDetalleCuota;
	}

	public double getnCuotaSindical() {
		return nCuotaSindical;
	}

	public void setnCuotaSindical(double nCuotaSindical) {
		this.nCuotaSindical = nCuotaSindical;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getIntereses() {
		return intereses;
	}

	public void setIntereses(double intereses) {
		this.intereses = intereses;
	}

	public double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public boolean isEstadoPago() {
		return estadoPago;
	}

	public void setEstadoPago(boolean estadoPago) {
		this.estadoPago = estadoPago;
	}

	public AcuerdosPagoModel getAcuerdo() {
		return acuerdo;
	}

	public void setAcuerdo(AcuerdosPagoModel acuerdo) {
		this.acuerdo = acuerdo;
	}

	@Override
	public String toString() {
		return "DetalleAcuerdoPagosCuotaModel [idDetalleCuota=" + idDetalleCuota + ", nCuotaSindical=" + nCuotaSindical
				+ ", subTotal=" + subTotal + ", intereses=" + intereses + ", importeTotal=" + importeTotal
				+ ", fechaVencimiento=" + fechaVencimiento + ", estadoPago=" + estadoPago + ", acuerdo=" + acuerdo
				+ "]";
	}
}
