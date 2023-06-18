package com.fatfa.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fatfa.utils.Constantes;

@Entity
@Table(name = "tb_solicitud_usuario_empresa")
public class SolicitudesUsuarioEmpresaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_solicitud" , nullable = false)
	private int idSolicitud;
	
	@NotEmpty(message = "El campo nombre es requerido.")
	@Column(name = "nombre", length = 70, nullable = false)
	private String nombre;
	
	@NotEmpty(message = "El campo nombre es requerido.")
	@Column(name = "apellidos", length = 70, nullable = false)
	private String apellidos;
	
	@NotEmpty(message = "El campo email es requerido")
	@Email(message = "No es un formato de tipo email",regexp = Constantes.EXPRESION_REGULAR_FORMATO_EMAIL)
	@Column(name="email", length = 80, nullable = false, unique = true)
	private String email;
	
	@Column(name="password", length = 180, nullable = false)
	private String contrasenia;
	
	@OneToOne
	@JoinColumn(name = "id_perfil", nullable = false)
	private PerfilesModel perfil;
	
	@OneToOne
	@JoinColumn(name = "id_empresa", nullable = false)
	private EmpresasModel empresa;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "estado_solicitud", nullable = false, length = 30)
	private EstadoSolicitud estadoSolicitud;	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm",locale = "es-PE", timezone = "America/Lima")
	@Column(name = "fecha_solicita", nullable = false,columnDefinition = "DATETIME" )
	private Date fechaSolicita;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm",locale = "es-PE", timezone = "America/Lima")
	@Column(name = "fecha_aprueba_solicitud", nullable = true,columnDefinition = "DATETIME" )
	private Date fechaApruebaSolcitud;
	
	@Column(name = "observaciones", nullable = true, columnDefinition = "TEXT")
	private String observaciones;

	public enum EstadoSolicitud{
		PENDIENTE,APROBADO,ANULADA
	}
	
	public SolicitudesUsuarioEmpresaModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SolicitudesUsuarioEmpresaModel(int idSolicitud,
			@NotEmpty(message = "El campo nombre es requerido.") String nombre,
			@NotEmpty(message = "El campo nombre es requerido.") String apellidos,
			@NotEmpty(message = "El campo email es requerido") @Email(message = "No es un formato de tipo email", regexp = "^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") String email,
			String contrasenia, PerfilesModel perfil, EmpresasModel empresa, EstadoSolicitud estadoSolicitud,
			Date fechaSolicita, Date fechaApruebaSolcitud, String observaciones) {
		super();
		this.idSolicitud = idSolicitud;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.contrasenia = contrasenia;
		this.perfil = perfil;
		this.empresa = empresa;
		this.estadoSolicitud = estadoSolicitud;
		this.fechaSolicita = fechaSolicita;
		this.fechaApruebaSolcitud = fechaApruebaSolcitud;
		this.observaciones = observaciones;
	}

	public int getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(int idSolicitud) {
		this.idSolicitud = idSolicitud;
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

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public PerfilesModel getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilesModel perfil) {
		this.perfil = perfil;
	}

	public EmpresasModel getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresasModel empresa) {
		this.empresa = empresa;
	}

	public EstadoSolicitud getEstadoSolicitud() {
		return estadoSolicitud;
	}

	public void setEstadoSolicitud(EstadoSolicitud estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}

	public Date getFechaSolicita() {
		return fechaSolicita;
	}

	public void setFechaSolicita(Date fechaSolicita) {
		this.fechaSolicita = fechaSolicita;
	}

	public Date getFechaApruebaSolcitud() {
		return fechaApruebaSolcitud;
	}

	public void setFechaApruebaSolcitud(Date fechaApruebaSolcitud) {
		this.fechaApruebaSolcitud = fechaApruebaSolcitud;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Override
	public String toString() {
		return "SolicitudesUsuarioEmpresaModel [idSolicitud=" + idSolicitud + ", nombre=" + nombre + ", apellidos="
				+ apellidos + ", email=" + email + ", contrasenia=" + contrasenia + ", perfil=" + perfil + ", empresa="
				+ empresa + ", estadoSolicitud=" + estadoSolicitud + ", fechaSolicita=" + fechaSolicita
				+ ", fechaApruebaSolcitud=" + fechaApruebaSolcitud + ", observaciones=" + observaciones + "]";
	}
}
