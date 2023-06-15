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
@Table(name = "tb_nominas")
public class NominasModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_nomina")
	private int idNomina ;
	
	@Column(length = 11, nullable = false)
	private String cuil;
	
	@Column(length = 100, nullable = false)
	private String nombres;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",locale = "es-PE", timezone = "America/Lima")
	@Column(name = "fechaingreso", nullable = true)
	private Date fechaIngreso;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",locale = "es-PE", timezone = "America/Lima")
	@Column(name = "fechaegreso", nullable = true)
	private Date fechaEgreso;
	
	@Column(nullable = true, columnDefinition = "decimal(18,2)")
	private double sueldo;
	
	@Column(name="estado_baja",nullable = true, columnDefinition = "BIT")
	private boolean estadoBaja;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",locale = "es-PE", timezone = "America/Lima")
	@Column(name = "fechabaja", nullable = true)
	private Date fechaBaja;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",locale = "es-PE", timezone = "America/Lima")
	@Column(name = "fecha_procesa", nullable = true)
	private Date fechaProcesa;

	@Column(name = "jornada_reducida", nullable = true, columnDefinition = "BIT")
	private boolean jornadaReducida;
	
	@Column(name = "mes", length = 2, nullable = true, columnDefinition = "CHAR(2)")
	private String mes;
	
	@Column(name = "anio", length = 4, nullable = true, columnDefinition = "CHAR(4)")
	private String anio;
	
	@Column(nullable = false, columnDefinition = "INT default 0")
	private int  rectificativa;
	
	
	@Column(name="monto_sac",nullable = true, columnDefinition = "decimal(18,2)")
	private double montoSac ;

	@Column(nullable = true, columnDefinition = "BIT")
	private boolean licencia;
	
	
	@Column(name="afiliado_obra_social",nullable = true, columnDefinition = "BIT")
	private boolean afiliadoObraSocial;
	
	@Column(nullable = true, columnDefinition = "TEXT")
	private String observaciones;
	
	@Column(name = "cantidad_dias_trabajados", nullable = true)
	private int cantidadDiasTrabajados;
		
	@Column(name = "nombre_archivo", nullable = true, length = 70)
	private String nombreArchivo;
	
	@OneToOne()
	@JoinColumn(name = "id_categoria", nullable = false)
	private CategoriasModelo categoria;
	
	@OneToOne
	@JoinColumn(name = "id_sindicato", nullable = false)
	private SindicatosModel sindicato;

	@OneToOne
	@JoinColumn(name = "id_zona", nullable = false)
	private ZonasModel zona;
	
	@OneToOne
	@JoinColumn(name = "id_empresa", nullable = false)
	private EmpresasModel empresa;

	public NominasModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public NominasModel(int idNomina, String cuil, String nombres, Date fechaIngreso, Date fechaEgreso, double sueldo,
			boolean estadoBaja, Date fechaBaja, Date fechaProcesa, boolean jornadaReducida, String mes, String anio,
			int rectificativa, double montoSac, boolean licencia, boolean afiliadoObraSocial, String observaciones,
			int cantidadDiasTrabajados, String nombreArchivo, CategoriasModelo categoria, SindicatosModel sindicato,
			ZonasModel zona, EmpresasModel empresa) {
		super();
		this.idNomina = idNomina;
		this.cuil = cuil;
		this.nombres = nombres;
		this.fechaIngreso = fechaIngreso;
		this.fechaEgreso = fechaEgreso;
		this.sueldo = sueldo;
		this.estadoBaja = estadoBaja;
		this.fechaBaja = fechaBaja;
		this.fechaProcesa = fechaProcesa;
		this.jornadaReducida = jornadaReducida;
		this.mes = mes;
		this.anio = anio;
		this.rectificativa = rectificativa;
		this.montoSac = montoSac;
		this.licencia = licencia;
		this.afiliadoObraSocial = afiliadoObraSocial;
		this.observaciones = observaciones;
		this.cantidadDiasTrabajados = cantidadDiasTrabajados;
		this.nombreArchivo = nombreArchivo;
		this.categoria = categoria;
		this.sindicato = sindicato;
		this.zona = zona;
		this.empresa = empresa;
	}

	public NominasModel(String cuil, String nombres, Date fechaIngreso, Date fechaEgreso, double sueldo,
			boolean estadoBaja, Date fechaBaja, Date fechaProcesa, boolean jornadaReducida, String mes, String anio,
			int rectificativa, double montoSac, boolean licencia, boolean afiliadoObraSocial, String observaciones,
			int cantidadDiasTrabajados, String nombreArchivo, CategoriasModelo categoria, SindicatosModel sindicato,
			ZonasModel zona, EmpresasModel empresa) {
		super();
		this.cuil = cuil;
		this.nombres = nombres;
		this.fechaIngreso = fechaIngreso;
		this.fechaEgreso = fechaEgreso;
		this.sueldo = sueldo;
		this.estadoBaja = estadoBaja;
		this.fechaBaja = fechaBaja;
		this.fechaProcesa = fechaProcesa;
		this.jornadaReducida = jornadaReducida;
		this.mes = mes;
		this.anio = anio;
		this.rectificativa = rectificativa;
		this.montoSac = montoSac;
		this.licencia = licencia;
		this.afiliadoObraSocial = afiliadoObraSocial;
		this.observaciones = observaciones;
		this.cantidadDiasTrabajados = cantidadDiasTrabajados;
		this.nombreArchivo = nombreArchivo;
		this.categoria = categoria;
		this.sindicato = sindicato;
		this.zona = zona;
		this.empresa = empresa;
	}



	public int getIdNomina() {
		return idNomina;
	}

	public void setIdNomina(int idNomina) {
		this.idNomina = idNomina;
	}

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(Date fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	public boolean isEstadoBaja() {
		return estadoBaja;
	}

	public void setEstadoBaja(boolean estadoBaja) {
		this.estadoBaja = estadoBaja;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Date getFechaProcesa() {
		return fechaProcesa;
	}

	public void setFechaProcesa(Date fechaProcesa) {
		this.fechaProcesa = fechaProcesa;
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

	public int getRectificativa() {
		return rectificativa;
	}

	public void setRectificativa(int rectificativa) {
		this.rectificativa = rectificativa;
	}

	public double getMontoSac() {
		return montoSac;
	}

	public void setMontoSac(double montoSac) {
		this.montoSac = montoSac;
	}

	public boolean isLicencia() {
		return licencia;
	}

	public void setLicencia(boolean licencia) {
		this.licencia = licencia;
	}

	public boolean isAfiliadoObraSocial() {
		return afiliadoObraSocial;
	}

	public void setAfiliadoObraSocial(boolean afiliadoObraSocial) {
		this.afiliadoObraSocial = afiliadoObraSocial;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getCantidadDiasTrabajados() {
		return cantidadDiasTrabajados;
	}

	public void setCantidadDiasTrabajados(int cantidadDiasTrabajados) {
		this.cantidadDiasTrabajados = cantidadDiasTrabajados;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public CategoriasModelo getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriasModelo categoria) {
		this.categoria = categoria;
	}

	public SindicatosModel getSindicato() {
		return sindicato;
	}

	public void setSindicato(SindicatosModel sindicato) {
		this.sindicato = sindicato;
	}

	public ZonasModel getZona() {
		return zona;
	}

	public void setZona(ZonasModel zona) {
		this.zona = zona;
	}

	public EmpresasModel getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresasModel empresa) {
		this.empresa = empresa;
	}

	public boolean getJornadaReducida() {
		return jornadaReducida;
	}

	public void setJornadaReducida(boolean jornadaReducida) {
		this.jornadaReducida = jornadaReducida;
	}



	@Override
	public String toString() {
		return "NominasModel [idNomina=" + idNomina + ", cuil=" + cuil + ", nombres=" + nombres + ", fechaIngreso="
				+ fechaIngreso + ", fechaEgreso=" + fechaEgreso + ", sueldo=" + sueldo + ", estadoBaja=" + estadoBaja
				+ ", fechaBaja=" + fechaBaja + ", fechaProcesa=" + fechaProcesa + ", jornadaReducida=" + jornadaReducida
				+ ", mes=" + mes + ", anio=" + anio + ", rectificativa=" + rectificativa + ", montoSac=" + montoSac
				+ ", licencia=" + licencia + ", afiliadoObraSocial=" + afiliadoObraSocial + ", observaciones="
				+ observaciones + ", cantidadDiasTrabajados=" + cantidadDiasTrabajados + ", nombreArchivo="
				+ nombreArchivo + ", categoria=" + categoria + ", sindicato=" + sindicato + ", zona=" + zona
				+ ", empresa=" + empresa + "]";
	}	
}
