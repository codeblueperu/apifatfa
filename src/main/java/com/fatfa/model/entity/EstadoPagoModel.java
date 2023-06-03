package com.fatfa.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_estado_pago")
public class EstadoPagoModel {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_estado_pago", nullable = false)
	private int idEstadoPago ;
	
	@Column(length = 25)
	private String nombre;

	public EstadoPagoModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EstadoPagoModel(int idEstadoPago, String nombre) {
		super();
		this.idEstadoPago = idEstadoPago;
		this.nombre = nombre;
	}

	public int getIdEstadoPago() {
		return idEstadoPago;
	}

	public void setIdEstadoPago(int idEstadoPago) {
		this.idEstadoPago = idEstadoPago;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "EstadoPagoModel [idEstadoPago=" + idEstadoPago + ", nombre=" + nombre + "]";
	}
	
	
}
