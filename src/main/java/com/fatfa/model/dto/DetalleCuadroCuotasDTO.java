package com.fatfa.model.dto;

import java.util.Date;

public class DetalleCuadroCuotasDTO {
	
	private int numCuota;
	
	private float valorResidual;
	
	private float interes;
	
	private float cuotaP;
	
	private float cuota;
	
	private float cuotaTotalConIntereses;
	
	private Date fechaVencimiento;

	public DetalleCuadroCuotasDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DetalleCuadroCuotasDTO(int numCuota, float valorResidual, float interes, float cuotaP, float cuota,
			float cuotaTotalConIntereses, Date fechaVencimiento) {
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

	public float getValorResidual() {
		return valorResidual;
	}

	public void setValorResidual(float valorResidual) {
		this.valorResidual = valorResidual;
	}

	public float getInteres() {
		return interes;
	}

	public void setInteres(float interes) {
		this.interes = interes;
	}

	public float getCuotaP() {
		return cuotaP;
	}

	public void setCuotaP(float cuotaP) {
		this.cuotaP = cuotaP;
	}

	public float getCuota() {
		return cuota;
	}

	public void setCuota(float cuota) {
		this.cuota = cuota;
	}

	public float getCuotaTotalConIntereses() {
		return cuotaTotalConIntereses;
	}

	public void setCuotaTotalConIntereses(float cuotaTotalConIntereses) {
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
