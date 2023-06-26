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
@Table(name = "tb_transferencia")
public class TransferenciaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_transferencia")
	private int idTransferencia ;
	
	@OneToOne
	@JoinColumn(name = "id_empresa", nullable = false)
	private EmpresasModel empresa;
	
	@Column(name = "numero_cuenta", length = 1, nullable = false)
	private int numeroCuenta;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",locale = "es-PE", timezone = "America/Lima")
	@Column(name = "fecha_pago", nullable = false,columnDefinition = "DATETIME" )
	private Date fechaPago;
	
	@Column(name = "mes", length = 2, nullable = false, columnDefinition = "CHAR(2)")
	private String mes;
	
	@Column(name = "anio", length = 4, nullable = false, columnDefinition = "CHAR(4)")
	private String anio;
	
	@Column(name = "importe_total", nullable = false, columnDefinition = "DECIMAL(18,2)")
	private Double importeTotal;
	
	@Column(name = "nombre_archivo", nullable = true, length = 70)
	private String comprobante;
	
	@Column(name = "detalle_observaciones", nullable = true, columnDefinition = "TEXT")
	private String detalleObservaciones;
	
	@Column(name = "observaciones", nullable = false)
	private int observaciones;
	
	@Column(name = "estado_solicitud", nullable = false, length = 30)
	private String estadoSolicitud;	
	
	@OneToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private UsuarioModel idUsuario;

	public int getIdTransferencia() {
		return idTransferencia;
	}

	public void setIdTransferencia(int idTransferencia) {
		this.idTransferencia = idTransferencia;
	}

	public EmpresasModel getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresasModel empresa) {
		this.empresa = empresa;
	}

	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public Double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public String getComprobante() {
		return comprobante;
	}

	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	public String getDetalleObservaciones() {
		return detalleObservaciones;
	}

	public void setDetalleObservaciones(String detalleObservaciones) {
		this.detalleObservaciones = detalleObservaciones;
	}

	public int getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(int observaciones) {
		this.observaciones = observaciones;
	}

	public String getEstadoSolicitud() {
		return estadoSolicitud;
	}

	public void setEstadoSolicitud(String estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}

	public UsuarioModel getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(UsuarioModel idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public String toString() {
		return "TransferenciaModel [idTransferencia=" + idTransferencia + ", empresa=" + empresa + ", numeroCuenta="
				+ numeroCuenta + ", fechaPago=" + fechaPago + ", mes=" + mes + ", anio=" + anio + ", importeTotal="
				+ importeTotal + ", comprobante=" + comprobante + ", detalleObservaciones=" + detalleObservaciones
				+ ", observaciones=" + observaciones + ", estadoSolicitud=" + estadoSolicitud + ", idUsuario="
				+ idUsuario + "]";
	}

	public TransferenciaModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
