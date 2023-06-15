package com.fatfa.model.dto;

public class MatrizPeriodoIntersDTO {

	private String periodo;
	private double interesMensual;
	private double interesDiario;

	public MatrizPeriodoIntersDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MatrizPeriodoIntersDTO(String periodo, double interesMensual, double interesDiario) {
		super();
		this.periodo = periodo;
		this.interesMensual = interesMensual;
		this.interesDiario = interesDiario;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
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
		return "MatrizPeriodoIntersDTO [periodo=" + periodo + ", interesMensual=" + interesMensual + ", interesDiario="
				+ interesDiario + "]";
	}
}
