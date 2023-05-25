package com.fatfa.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_responsables_ddjj")
public class ResponsableDDJJModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_responsable", nullable = false)
	private int idResponsable;

	@Column(name = "nombre", length = 50, nullable = false)
	private String nombre;
	
	@Column(name = "apellidos", length = 100, nullable = false)
	private String apellidos;
	
	@Column(name = "email", length = 80,nullable = false)
	private String email;
	
	@Column(name = "telefono", length = 25, nullable = false)
	private String telefono;

	public ResponsableDDJJModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponsableDDJJModel(int idResponsable, String nombre, String apellidos, String email, String telefono) {
		super();
		this.idResponsable = idResponsable;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;
	}

	public int getIdResponsable() {
		return idResponsable;
	}

	public void setIdResponsable(int idResponsable) {
		this.idResponsable = idResponsable;
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
		return "ResponsableDDJJModel [idResponsable=" + idResponsable + ", nombre=" + nombre + ", apellidos="
				+ apellidos + ", email=" + email + ", telefono=" + telefono + "]";
	}

//	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinColumn(name = "id_empresa")
//	private EmpresasModel empresa;

	
}
