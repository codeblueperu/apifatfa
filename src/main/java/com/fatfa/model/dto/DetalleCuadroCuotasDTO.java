package com.fatfa.model.dto;

import java.util.Date;

public class DetalleCuadroCuotasDTO {
	
	private int numCuota;
	
	private double valorResidual;
	
	private double interes;
	
	private double cuotaP;
	
	private double cuota;
	
	private double cuotaTotalConIntereses;
	
	private Date fechaVencimiento;

	public DetalleCuadroCuotasDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DetalleCuadroCuotasDTO(int numCuota, double valorResidual, double interes, double cuotaP, double cuota,
			double cuotaTotalConIntereses, Date fechaVencimiento) {
		super();
		this.numCuota = numCuota;
		this.valorResidual = valorResidual;
		this.interes = interes;
		this.cuotaP = cuotaP;
		this.cuota = cuota;
		this.cuotaTotalConIntereses = cuotaTotalConIntereses;
		this.fechaVencimiento = fechaVencimiento;
	}

	public int getNumCuota() {
		return numCuota;
	}

	public void setNumCuota(int numCuota) {
		this.numCuota = numCuota;
	}

	public double getValorResidual() {
		return valorResidual;
	}

	public void setValorResidual(double valorResidual) {
		this.valorResidual = valorResidual;
	}

	public double getInteres() {
		return interes;
	}

	public void setInteres(double interes) {
		this.interes = interes;
	}

	public double getCuotaP() {
		return cuotaP;
	}

	public void setCuotaP(double cuotaP) {
		this.cuotaP = cuotaP;
	}

	public double getCuota() {
		return cuota;
	}

	public void setCuota(double cuota) {
		this.cuota = cuota;
	}

	public double getCuotaTotalConIntereses() {
		return cuotaTotalConIntereses;
	}

	public void setCuotaTotalConIntereses(double cuotaTotalConIntereses) {
		this.cuotaTotalConIntereses = cuotaTotalConIntereses;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	@Override
	public String toString() {
		return "DetalleCuadroCuotasDTO [numCuota=" + numCuota + ", valorResidual=" + valorResidual + ", interes="
				+ interes + ", cuotaP=" + cuotaP + ", cuota=" + cuota + ", cuotaTotalConIntereses="
				+ cuotaTotalConIntereses + ", fechaVencimiento=" + fechaVencimiento + "]";
	}
	
	

}
