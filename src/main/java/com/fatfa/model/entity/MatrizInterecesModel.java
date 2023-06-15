package com.fatfa.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_matriz_intereces")
public class MatrizInterecesModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_interes")
	private int idInteres;
	
	@Column(name = "norma", length = 45, nullable = false)
	private String norma;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",locale = "es-PE", timezone = "America/Lima")
	@Column(name = "fecha_inicio", nullable = false, columnDefinition = "DATETIME")
	private Date fechaInicio;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",locale = "es-PE", timezone = "America/Lima")
	@Column(name = "fecha_termino", nullable = false, columnDefinition = "DATETIME")
	private Date fechaTermino;
	
	@Column(name = "interes_mensual", nullable = false)
	private double interesMensual;
	
	@Column(name = "interes_diario", nullable = false)
	private double interesDiario;

	public MatrizInterecesModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MatrizInterecesModel(int idInteres, String norma, Date fechaInicio, Date fechaTermino, double interesMensual,
			double interesDiario) {
		super();
		this.idInteres = idInteres;
		this.norma = norma;
		this.fechaInicio = fechaInicio;
		this.fechaTermino = fechaTermino;
		this.interesMensual = interesMensual;
		this.interesDiario = interesDiario;
	}

	public int getIdInteres() {
		return idInteres;
	}

	public void setIdInteres(int idInteres) {
		this.idInteres = idInteres;
	}

	public String getNorma() {
		return norma;
	}

	public void setNorma(String norma) {
		this.norma = norma;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaTermino() {
		return fechaTermino;
	}

	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}

	public double getInteresMensual() {
		return interesMensual;
	}

	public void setInteresMensual(double interesMensual) {
		this.interesMensual = interesMensual;
	}

	public double getInteresDiario() {
		return interesDiario;
	}

	public void setInteresDiario(double interesDiario) {
		this.interesDiario = interesDiario;
	}

	@Override
	public String toString() {
		return "MatrizInterecesModel [idInteres=" + idInteres + ", norma=" + norma + ", fechaInicio=" + fechaInicio
				+ ", fechaTermino=" + fechaTermino + ", interesMensual=" + interesMensual + ", interesDiario="
				+ interesDiario + "]";
	}
}
