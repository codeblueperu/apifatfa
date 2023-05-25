package com.fatfa.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_responsable_rrhh")
public class ResponsableRRHHModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_recursos_humanos", nullable = false)
	private int idRecusosHumanos;

	@Column(name = "nombre", length = 50, nullable = false)
	private String nombre;
	
	@Column(name = "apellidos", length = 100, nullable = false)
	private String apellidos;
	
	@Column(name = "email", length = 80,nullable = false)
	private String email;
	
	@Column(name = "telefono", length = 25, nullable = false)
	private String telefono;

	public ResponsableRRHHModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponsableRRHHModel(int idRecusosHumanos, String nombre, String apellidos, String email, String telefono) {
		super();
		this.idRecusosHumanos = idRecusosHumanos;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;
	}

	public int getidRecusosHumanos() {
		return idRecusosHumanos;
	}

	public void setidRecusosHumanos(int idRecusosHumanos) {
		this.idRecusosHumanos = idRecusosHumanos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "ResponsableRRHHModel [idRecusosHumanos=" + idRecusosHumanos + ", nombre=" + nombre + ", apellidos="
				+ apellidos + ", email=" + email + ", telefono=" + telefono + "]";
	}
}
