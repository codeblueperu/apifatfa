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
@Table(name = "tb_depositos")
public class DepositosModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_deposito", nullable = false)
	private int idDeposito;
	
	@Column(name = "cuenta_bancaria", length = 1, nullable = false)
	private int cuentaBancaria;
	
	@OneToOne
	@JoinColumn(name = "id_empresa", nullable = false)
	private EmpresasModel empresa;
	
	@Column(name = "importe_total", nullable = false, columnDefinition = "DECIMAL(18,2)")
	private Double importeTotal;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",locale = "es-PE", timezone = "America/Lima")
	@Column(name = "fecha_pago", nullable = false)
	private Date fechaPago;
	
	@Column(name = "archivo", nullable = true, length = 70)
	private String archivo;	
	
	@Column(name = "mes", length = 2, nullable = false, columnDefinition = "CHAR(2)")
	private String mes;
	
	@Column(name = "anio", length = 4, nullable = false, columnDefinition = "CHAR(4)")
	private String anio;
	
	@OneToOne
	@JoinColumn(name = "id_estado_pago", nullable = false)
	private EstadoPagoModel estadoPago;

	@Override
	public String toString() {
		return "DepositosModel [idDeposito=" + idDeposito + ", cuentaBancaria=" + cuentaBancaria + ", empresa="
				+ empresa + ", importeTotal=" + importeTotal + ", fechaPago=" + fechaPago + ", archivo=" + archivo
				+ ", mes=" + mes + ", anio=" + anio + ", estadoPago=" + estadoPago + "]";
	}

	
	public int getIdDeposito() {
		return idDeposito;
	}


	public void setIdDeposito(int idDeposito) {
		this.idDeposito = idDeposito;
	}


	public int getCuentaBancaria() {
		return cuentaBancaria;
	}


	public void setCuentaBancaria(int cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}


	public EmpresasModel getEmpresa() {
		return empresa;
	}


	public void setEmpresa(EmpresasModel empresa) {
		this.empresa = empresa;
	}


	public Double getImporteTotal() {
		return importeTotal;
	}


	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}


	public Date getFechaPago() {
		return fechaPago;
	}


	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}


	public String getArchivo() {
		return archivo;
	}


	public void setArchivo(String archivo) {
		this.archivo = archivo;
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


	public EstadoPagoModel getEstadoPago() {
		return estadoPago;
	}


	public void setEstadoPago(EstadoPagoModel estadoPago) {
		this.estadoPago = estadoPago;
	}


	public DepositosModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
