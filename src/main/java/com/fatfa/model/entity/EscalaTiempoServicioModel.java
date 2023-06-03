package com.fatfa.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_escala_tiempo_servicio")
public class EscalaTiempoServicioModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tiempo", nullable = false)
	private int idTiempo;
	
	private int tiempoServicio;
	
	private double porcentaje;
	
	@Column(length = 5)
	private String porcentajeDescripcion;

	public EscalaTiempoServicioModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EscalaTiempoServicioModel(int idTiempo, int tiempoServicio, double porcentaje,
			String porcentajeDescripcion) {
		super();
		this.idTiempo = idTiempo;
		this.tiempoServicio = tiempoServicio;
		this.porcentaje = porcentaje;
		this.porcentajeDescripcion = porcentajeDescripcion;
	}

	public int getIdTiempo() {
		return idTiempo;
	}

	public void setIdTiempo(int idTiempo) {
		this.idTiempo = idTiempo;
	}

	public int getTiempoServicio() {
		return tiempoServicio;
	}

	public void setTiempoServicio(int tiempoServicio) {
		this.tiempoServicio = tiempoServicio;
	}

	public double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}

	public String getPorcentajeDescripcion() {
		return porcentajeDescripcion;
	}

	public void setPorcentajeDescripcion(String porcentajeDescripcion) {
		this.porcentajeDescripcion = porcentajeDescripcion;
	}

	@Override
	public String toString() {
		return "EscalaTiempoServicioModel [idTiempo=" + idTiempo + ", tiempoServicio=" + tiempoServicio
				+ ", porcentaje=" + porcentaje + ", porcentajeDescripcion=" + porcentajeDescripcion + "]";
	}	
}
