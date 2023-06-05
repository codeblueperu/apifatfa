package com.fatfa.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_detalle_concepto_boleta")
public class DetalleBoletaConceptoModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_detalle_aporte", nullable = false)
	private int idDetalleConcepto ;
	
	@Column(name = "aporte_art46")
	private double aporteArt46;
	
	@Column(name = "aporte_art47")
	private double aporteArt47;
	
	@Column(name = "aporte_art48")
	private double aporteArt48;
	
	@Column(name = "intereses")
	private double intereses;
	
	@Column(name = "otros")
	private double otros;
	
	@Column(name = "total_pagar")
	private double totalApagar;
	

	public DetalleBoletaConceptoModel() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DetalleBoletaConceptoModel(double aporteArt46, double aporteArt47, double aporteArt48, double intereses,
			double otros, double totalApagar) {
		super();
		this.aporteArt46 = aporteArt46;
		this.aporteArt47 = aporteArt47;
		this.aporteArt48 = aporteArt48;
		this.intereses = intereses;
		this.otros = otros;
		this.totalApagar = totalApagar;
	}


	public int getIdDetalleConcepto() {
		return idDetalleConcepto;
	}


	public void setIdDetalleConcepto(int idDetalleConcepto) {
		this.idDetalleConcepto = idDetalleConcepto;
	}


	public double getAporteArt46() {
		return aporteArt46;
	}


	public void setAporteArt46(double aporteArt46) {
		this.aporteArt46 = aporteArt46;
	}


	public double getAporteArt47() {
		return aporteArt47;
	}


	public void setAporteArt47(double aporteArt47) {
		this.aporteArt47 = aporteArt47;
	}


	public double getAporteArt48() {
		return aporteArt48;
	}


	public void setAporteArt48(double aporteArt48) {
		this.aporteArt48 = aporteArt48;
	}


	public double getIntereses() {
		return intereses;
	}


	public void setIntereses(double intereses) {
		this.intereses = intereses;
	}


	public double getOtros() {
		return otros;
	}


	public void setOtros(double otros) {
		this.otros = otros;
	}


	public double getTotalApagar() {
		return totalApagar;
	}


	public void setTotalApagar(double totalApagar) {
		this.totalApagar = totalApagar;
	}


	@Override
	public String toString() {
		return "DetalleBoletaConceptoModel [idDetalleConcepto=" + idDetalleConcepto + ", aporteArt46=" + aporteArt46
				+ ", aporteArt47=" + aporteArt47 + ", aporteArt48=" + aporteArt48 + ", intereses=" + intereses
				+ ", otros=" + otros + ", totalApagar=" + totalApagar + "]";
	}
}
