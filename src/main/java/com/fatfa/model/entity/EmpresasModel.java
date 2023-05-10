package com.fatfa.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_empresas")
public class EmpresasModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empresa", nullable = false)
	private int idEmpresa;
	
	@Column(length = 11, nullable = false, unique = true)
	private String cuit;
	
	@Column(name = "razon_social", length = 70, nullable = false)
	private String razonSocial;
	
	@Column(name = "nombre_fantasia", length = 70, nullable = false)
	private String nombreFantasia;
	
	@Column(name = "numero_afiliacion", nullable = true)
	private int numeroAfiliacion;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",locale = "es-PE", timezone = "America/Lima")
	@Column(name = "periodo_inicial", nullable = true)
	private Date periodoInicial;
	
	@Column(name = "activo", nullable = false)
	private boolean activo;
	
	@Column(name = "observaciones", nullable = true, columnDefinition = "text")
	private String observaciones;
	
	@Column(name = "trabajadores_afectados_por_convenio", nullable = false)
	private int trabajadoresAfectadosPorConvenio;
	
	@Column(name = "trabajadores_afectados_por_obra_social", nullable = false)
	private int trabajadoresAfectadosPorObraSocial;

	public EmpresasModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmpresasModel(int idEmpresa, String cuit, String razonSocial, String nombreFantasia, int numeroAfiliacion,
			Date periodoInicial, boolean activo, String observaciones, int trabajadoresAfectadosPorConvenio,
			int trabajadoresAfectadosPorObraSocial) {
		super();
		this.idEmpresa = idEmpresa;
		this.cuit = cuit;
		this.razonSocial = razonSocial;
		this.nombreFantasia = nombreFantasia;
		this.numeroAfiliacion = numeroAfiliacion;
		this.periodoInicial = periodoInicial;
		this.activo = activo;
		this.observaciones = observaciones;
		this.trabajadoresAfectadosPorConvenio = trabajadoresAfectadosPorConvenio;
		this.trabajadoresAfectadosPorObraSocial = trabajadoresAfectadosPorObraSocial;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getNombreFantasia() {
		return nombreFantasia;
	}

	public void setNombreFantasia(String nombreFantasia) {
		this.nombreFantasia = nombreFantasia;
	}

	public int getNumeroAfiliacion() {
		return numeroAfiliacion;
	}

	public void setNumeroAfiliacion(int numeroAfiliacion) {
		this.numeroAfiliacion = numeroAfiliacion;
	}

	public Date getPeriodoInicial() {
		return periodoInicial;
	}

	public void setPeriodoInicial(Date periodoInicial) {
		this.periodoInicial = periodoInicial;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getTrabajadoresAfectadosPorConvenio() {
		return trabajadoresAfectadosPorConvenio;
	}

	public void setTrabajadoresAfectadosPorConvenio(int trabajadoresAfectadosPorConvenio) {
		this.trabajadoresAfectadosPorConvenio = trabajadoresAfectadosPorConvenio;
	}

	public int getTrabajadoresAfectadosPorObraSocial() {
		return trabajadoresAfectadosPorObraSocial;
	}

	public void setTrabajadoresAfectadosPorObraSocial(int trabajadoresAfectadosPorObraSocial) {
		this.trabajadoresAfectadosPorObraSocial = trabajadoresAfectadosPorObraSocial;
	}

	@Override
	public String toString() {
		return "EmpresasModel [idEmpresa=" + idEmpresa + ", cuit=" + cuit + ", razonSocial=" + razonSocial
				+ ", nombreFantasia=" + nombreFantasia + ", numeroAfiliacion=" + numeroAfiliacion + ", periodoInicial="
				+ periodoInicial + ", activo=" + activo + ", observaciones=" + observaciones
				+ ", trabajadoresAfectadosPorConvenio=" + trabajadoresAfectadosPorConvenio
				+ ", trabajadoresAfectadosPorObraSocial=" + trabajadoresAfectadosPorObraSocial + "]";
	}
}
