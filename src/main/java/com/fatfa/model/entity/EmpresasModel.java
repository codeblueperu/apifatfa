package com.fatfa.model.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
	
	@OneToOne
	@JoinColumn(name="id_sindicato", nullable = false)
	private SindicatosModel idSindicato;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "idResponsable")
	private ResponsableDDJJModel reponsableDJ;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idRecusosHumanos")
	private ResponsableRRHHModel responableRH;
	
	@OneToOne
	@JoinColumn(name = "id_actividad_economica")
	private ActividadesEconomicaModel idActividad;

	public EmpresasModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "EmpresasModel [idEmpresa=" + idEmpresa + ", cuit=" + cuit + ", razonSocial=" + razonSocial
				+ ", nombreFantasia=" + nombreFantasia + ", numeroAfiliacion=" + numeroAfiliacion + ", periodoInicial="
				+ periodoInicial + ", activo=" + activo + ", observaciones=" + observaciones
				+ ", trabajadoresAfectadosPorConvenio=" + trabajadoresAfectadosPorConvenio
				+ ", trabajadoresAfectadosPorObraSocial=" + trabajadoresAfectadosPorObraSocial + ", idSindicato="
				+ idSindicato + ", reponsableDJ=" + reponsableDJ + ", responableRH=" + responableRH + ", idActividad="
				+ idActividad + ", idConvenio=" + "]";
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

	public SindicatosModel getIdSindicato() {
		return idSindicato;
	}

	public void setIdSindicato(SindicatosModel idSindicato) {
		this.idSindicato = idSindicato;
	}

	public ResponsableDDJJModel getReponsableDJ() {
		return reponsableDJ;
	}

	public void setReponsableDJ(ResponsableDDJJModel reponsableDJ) {
		this.reponsableDJ = reponsableDJ;
	}

	public ResponsableRRHHModel getResponableRH() {
		return responableRH;
	}

	public void setResponableRH(ResponsableRRHHModel responableRH) {
		this.responableRH = responableRH;
	}

	public ActividadesEconomicaModel getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(ActividadesEconomicaModel idActividad) {
		this.idActividad = idActividad;
	}

}
