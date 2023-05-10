package com.fatfa.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_empresas_ejecutivos")
public class EmpresasEjecutivosModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empresa_ejecutivo", nullable = false)
	private int idEmpresaEjecutivo;

	@Column(name = "id_user_Asignacion", nullable = false)
	private int id_user_Asignacion;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm",locale = "es-PE", timezone = "America/Lima")
	@Column(name = "fecha_creacion", nullable = false, columnDefinition = "datetime")
	private Date fechaCreacion;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",locale = "es-PE", timezone = "America/Lima")
	@Column(name = "fecha_alta", nullable = false)
	private Date fechaAlta;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",locale = "es-PE", timezone = "America/Lima")
	@Column(name = "fecha_baja", nullable = false)
	private Date fechaBaja;
	
	@OneToOne
	@JoinColumn(name = "id_empresa")
	private EmpresasModel empresa;
	
	@OneToOne
	@JoinColumn(name = "id_usuario")
	private UsuarioModel  usuario;

	public EmpresasEjecutivosModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmpresasEjecutivosModel(int idEmpresaEjecutivo, int id_user_Asignacion, Date fechaCreacion, Date fechaAlta,
			Date fechaBaja, EmpresasModel empresa, UsuarioModel usuario) {
		super();
		this.idEmpresaEjecutivo = idEmpresaEjecutivo;
		this.id_user_Asignacion = id_user_Asignacion;
		this.fechaCreacion = fechaCreacion;
		this.fechaAlta = fechaAlta;
		this.fechaBaja = fechaBaja;
		this.empresa = empresa;
		this.usuario = usuario;
	}

	public int getIdEmpresaEjecutivo() {
		return idEmpresaEjecutivo;
	}

	public void setIdEmpresaEjecutivo(int idEmpresaEjecutivo) {
		this.idEmpresaEjecutivo = idEmpresaEjecutivo;
	}

	public int getId_user_Asignacion() {
		return id_user_Asignacion;
	}

	public void setId_user_Asignacion(int id_user_Asignacion) {
		this.id_user_Asignacion = id_user_Asignacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public EmpresasModel getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresasModel empresa) {
		this.empresa = empresa;
	}

	public UsuarioModel getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "EmpresasEjecutivosModel [idEmpresaEjecutivo=" + idEmpresaEjecutivo + ", id_user_Asignacion="
				+ id_user_Asignacion + ", fechaCreacion=" + fechaCreacion + ", fechaAlta=" + fechaAlta + ", fechaBaja="
				+ fechaBaja + ", empresa=" + empresa + ", usuario=" + usuario + "]";
	}
}
