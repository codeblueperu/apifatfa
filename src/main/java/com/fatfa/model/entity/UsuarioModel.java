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
@Table(name = "tb_usuario")
public class UsuarioModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario", nullable = false)
	private int idUsuario;
	
	@Column(name = "email", length = 80,nullable = false, unique = true)
	private String email;
	
	@Column(name = "password", length = 200, nullable = false)
	private String password ;
	
	@Column(name = "nombres_apellidos", length = 70, nullable = false)
	private String nombresApellidos;
	
	@Column(name = "estado_cuenta", nullable = false, columnDefinition = "BIT default 1")
	private boolean estadoCuenta ;
	
	@Column(name = "estado_cambio_password", nullable = false, columnDefinition = "BIT default 1")
	private boolean estadoCambioPassword;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",locale = "es-PE", timezone = "America/Lima")
	@Column(name = "fecha_cambio_clave", nullable = true)
	private Date fechaCambioClave;
	
	@Column(name = "estado_acceso", nullable = false, columnDefinition = "BIT default 1")
	private boolean estadoAcceso;	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm",locale = "es-PE", timezone = "America/Lima")
	@Column(name = "fecha_alta", nullable = true, columnDefinition = "datetime")
	private Date fechaAlta;
	
	@Column(name = "password_reset_token", length = 200, nullable = true)
	private String passwordResetToken ;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm",locale = "es-PE", timezone = "America/Lima")
	@Column(name = "password_reset_token_date", nullable = true, columnDefinition = "datetime")
	private Date  passwordResetTokenDate;
	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm",locale = "es-PE", timezone = "America/Lima")
	@Column(name = "fecha_modifica", nullable = true, columnDefinition = "datetime")
	private Date fechaModifica;
	
	@Column(name = "id_usuario_crea", nullable = false)
	private int idUsuarioCrea ;
	
	@OneToOne
	@JoinColumn(name = "id_perfil")
	private PerfilesModel perfil;

	public UsuarioModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsuarioModel(int idUsuario, String email, String password, String nombresApellidos, boolean estadoCuenta,
			boolean estadoCambioPassword, Date fechaCambioClave, boolean estadoAcceso, Date fechaAlta,
			String passwordResetToken, Date passwordResetTokenDate, Date fechaModifica, int idUsuarioCrea,
			PerfilesModel perfil) {
		super();
		this.idUsuario = idUsuario;
		this.email = email;
		this.password = password;
		this.nombresApellidos = nombresApellidos;
		this.estadoCuenta = estadoCuenta;
		this.estadoCambioPassword = estadoCambioPassword;
		this.fechaCambioClave = fechaCambioClave;
		this.estadoAcceso = estadoAcceso;
		this.fechaAlta = fechaAlta;
		this.passwordResetToken = passwordResetToken;
		this.passwordResetTokenDate = passwordResetTokenDate;
		this.fechaModifica = fechaModifica;
		this.idUsuarioCrea = idUsuarioCrea;
		this.perfil = perfil;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombresApellidos() {
		return nombresApellidos;
	}

	public void setNombresApellidos(String nombresApellidos) {
		this.nombresApellidos = nombresApellidos;
	}

	public boolean isEstadoCuenta() {
		return estadoCuenta;
	}

	public void setEstadoCuenta(boolean estadoCuenta) {
		this.estadoCuenta = estadoCuenta;
	}

	public boolean isEstadoCambioPassword() {
		return estadoCambioPassword;
	}

	public void setEstadoCambioPassword(boolean estadoCambioPassword) {
		this.estadoCambioPassword = estadoCambioPassword;
	}

	public Date getFechaCambioClave() {
		return fechaCambioClave;
	}

	public void setFechaCambioClave(Date fechaCambioClave) {
		this.fechaCambioClave = fechaCambioClave;
	}

	public boolean isEstadoAcceso() {
		return estadoAcceso;
	}

	public void setEstadoAcceso(boolean estadoAcceso) {
		this.estadoAcceso = estadoAcceso;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getPasswordResetToken() {
		return passwordResetToken;
	}

	public void setPasswordResetToken(String passwordResetToken) {
		this.passwordResetToken = passwordResetToken;
	}

	public Date getPasswordResetTokenDate() {
		return passwordResetTokenDate;
	}

	public void setPasswordResetTokenDate(Date passwordResetTokenDate) {
		this.passwordResetTokenDate = passwordResetTokenDate;
	}

	public Date getFechaModifica() {
		return fechaModifica;
	}

	public void setFechaModifica(Date fechaModifica) {
		this.fechaModifica = fechaModifica;
	}

	public int getIdUsuarioCrea() {
		return idUsuarioCrea;
	}

	public void setIdUsuarioCrea(int idUsuarioCrea) {
		this.idUsuarioCrea = idUsuarioCrea;
	}

	public PerfilesModel getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilesModel perfil) {
		this.perfil = perfil;
	}

	@Override
	public String toString() {
		return "UsuarioModel [idUsuario=" + idUsuario + ", email=" + email + ", password=" + password
				+ ", nombresApellidos=" + nombresApellidos + ", estadoCuenta=" + estadoCuenta
				+ ", estadoCambioPassword=" + estadoCambioPassword + ", fechaCambioClave=" + fechaCambioClave
				+ ", estadoAcceso=" + estadoAcceso + ", fechaAlta=" + fechaAlta + ", passwordResetToken="
				+ passwordResetToken + ", passwordResetTokenDate=" + passwordResetTokenDate + ", fechaModifica="
				+ fechaModifica + ", idUsuarioCrea=" + idUsuarioCrea + ", perfil=" + perfil + "]";
	}
}
