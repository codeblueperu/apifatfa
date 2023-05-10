package com.fatfa.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_estado_trabajadores")
public class EstadoTrabajadoresModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_estado_trabajador")
	private int idEstadoTrabajador ;
	
	@Column(name = "estado", length = 70, nullable = false)
	private String descripcionEstado;

	public EstadoTrabajadoresModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EstadoTrabajadoresModel(int idEstadoTrabajador, String descripcionEstado) {
		super();
		this.idEstadoTrabajador = idEstadoTrabajador;
		this.descripcionEstado = descripcionEstado;
	}

	public int getIdEstadoTrabajador() {
		return idEstadoTrabajador;
	}

	public void setIdEstadoTrabajador(int idEstadoTrabajador) {
		this.idEstadoTrabajador = idEstadoTrabajador;
	}

	public String getDescripcionEstado() {
		return descripcionEstado;
	}

	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}

	@Override
	public String toString() {
		return "EstadoTrabajadoresModel [idEstadoTrabajador=" + idEstadoTrabajador + ", descripcionEstado="
				+ descripcionEstado + "]";
	}
}
