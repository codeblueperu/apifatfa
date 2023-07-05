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
@Table(name = "tb_boletas_importacion")
public class BoletasImportacionModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "secuencial", nullable = false)
	private int secuencial;
	
	@OneToOne
	@JoinColumn(name = "id_boleta", nullable = false)
	private BoletaModel boleta;
	
	@Column(name = "nombre_archivo_importacion", length = 240, nullable = false)
	private String nombreArchivoimportacion;
	
	@OneToOne
	@JoinColumn(name = "id_banco", nullable = false)
	private BancosModel medioPago;
	
	@Column(name = "importe_cobrado", columnDefinition = "DECIMAL(18,2)", nullable = false)
	private double importeCobrado;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",locale = "es-PE", timezone = "America/Lima")
	@Column(name = "fecha_pago", nullable = false)
	private Date fechaPago;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",locale = "es-PE", timezone = "America/Lima")
	@Column(name = "fecha_importacion", nullable = false)
	private Date fechaImportacion;
	
	@Column(name = "id_usuario", nullable = false)
	private int idUserLogin;
	
	@Column(name = "contenido_importacion", nullable = false, columnDefinition = "TEXT")
	private String contenidoFile;

	public BoletasImportacionModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoletasImportacionModel(int secuencial, BoletaModel boleta, String nombreArchivoimportacion,
			BancosModel medioPago, double importeCobrado, Date fechaPago, Date fechaImportacion, int idUserLogin,
			String contenidoFile) {
		super();
		this.secuencial = secuencial;
		this.boleta = boleta;
		this.nombreArchivoimportacion = nombreArchivoimportacion;
		this.medioPago = medioPago;
		this.importeCobrado = importeCobrado;
		this.fechaPago = fechaPago;
		this.fechaImportacion = fechaImportacion;
		this.idUserLogin = idUserLogin;
		this.contenidoFile = contenidoFile;
	}

	public int getSecuencial() {
		return secuencial;
	}

	public void setSecuencial(int secuencial) {
		this.secuencial = secuencial;
	}

	public BoletaModel getBoleta() {
		return boleta;
	}

	public void setBoleta(BoletaModel boleta) {
		this.boleta = boleta;
	}

	public String getNombreArchivoimportacion() {
		return nombreArchivoimportacion;
	}

	public void setNombreArchivoimportacion(String nombreArchivoimportacion) {
		this.nombreArchivoimportacion = nombreArchivoimportacion;
	}

	public BancosModel getMedioPago() {
		return medioPago;
	}

	public void setMedioPago(BancosModel medioPago) {
		this.medioPago = medioPago;
	}

	public double getImporteCobrado() {
		return importeCobrado;
	}

	public void setImporteCobrado(double importeCobrado) {
		this.importeCobrado = importeCobrado;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Date getFechaImportacion() {
		return fechaImportacion;
	}

	public void setFechaImportacion(Date fechaImportacion) {
		this.fechaImportacion = fechaImportacion;
	}

	public int getIdUserLogin() {
		return idUserLogin;
	}

	public void setIdUserLogin(int idUserLogin) {
		this.idUserLogin = idUserLogin;
	}

	public String getContenidoFile() {
		return contenidoFile;
	}

	public void setContenidoFile(String contenidoFile) {
		this.contenidoFile = contenidoFile;
	}

	@Override
	public String toString() {
		return "BoletasImportacionModel [secuencial=" + secuencial + ", boleta=" + boleta
				+ ", nombreArchivoimportacion=" + nombreArchivoimportacion + ", medioPago=" + medioPago
				+ ", importeCobrado=" + importeCobrado + ", fechaPago=" + fechaPago + ", fechaImportacion="
				+ fechaImportacion + ", idUserLogin=" + idUserLogin + ", contenidoFile=" + contenidoFile + "]";
	}
}
