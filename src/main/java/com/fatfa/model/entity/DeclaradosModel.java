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
@Table(name = "tb_declarados")
public class DeclaradosModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_declarado")
	private int idDeclarado ;
	
	@Column(length = 11, nullable = false)
	private String cuit;
	
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
	
	@Column(name="localidad",length = 70, nullable = true)
	private String localidadNombre;
	
	@Column(name="estado_baja",nullable = true, columnDefinition = "BIT")
	private boolean estadoBaja;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",locale = "es-PE", timezone = "America/Lima")
	@Column(name = "fechabaja", nullable = true)
	private Date fechaBaja;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",locale = "es-PE", timezone = "America/Lima")
	@Column(name = "fecha", nullable = false)
	private Date fecha;
	
//	@Column(name = "localidad_trabajador", length = 70, nullable = false)
//	private String localidadTrabajador;
	
	@Column(name = "jornada_reducida", length = 4, nullable = true, columnDefinition = "CHAR(4)")
	private String jornadaReducida;
	
	@Column(name = "ingreso_ayudante", length = 60, nullable = true)
	private String ingresoAyudante;
	
	@Column(name = "mes", length = 2, nullable = true, columnDefinition = "CHAR(2)")
	private String mes;
	
	@Column(name = "anio", length = 4, nullable = true, columnDefinition = "CHAR(4)")
	private String anio;
	
	@Column(nullable = true)
	private int  rectificativa;
	
//	@Column(name="sueldo_remunerativo",nullable = true, columnDefinition = "decimal(18,2)")
//	private double sueldoRemunerativo;
	
	@Column(name="monto_sac",nullable = true, columnDefinition = "decimal(18,2)")
	private double montoSac ;
	
//	@Column(name="monto_vacaciones",nullable = true, columnDefinition = "decimal(18,2)")
//	private double montoVacaciones;

	@Column(nullable = true, columnDefinition = "BIT")
	private boolean licencia;
	
//	@Column(name="afiliado_federacion",nullable = true, columnDefinition = "BIT")
//	private boolean afiliadoFederacion;
	
	@Column(name="afiliado_obra_social",nullable = true, columnDefinition = "BIT")
	private boolean afiliadoObraSocial;
	
	@Column(nullable = true, columnDefinition = "TEXT")
	private String observaciones;
	
	@Column(name = "cantidad_dias_trabajados", nullable = true)
	private int cantidadDiasTrabajados;
	
//	@Column(name = "cantidad_dias_vacaciones", nullable = true)
//	private int cantidadDiasVacaciones;
	
//	@Column(name = "sueldo_no_remunerativo", nullable = true, columnDefinition = "decimal(18,2)")
//	private double sueldo_no_remunerativo ;

	
	@Column(name = "nombre_archivo", nullable = true, length = 70)
	private String nombreArchivo;
	
	@OneToOne()
	@JoinColumn(name = "id_categoria")
	private CategoriasModelo categoria;
	
	@OneToOne
	@JoinColumn(name = "id_localidad")
	private LocalidadModel localidad;
	
	@OneToOne
	@JoinColumn(name = "id_sindicato")
	private SindicatosModel sindicato;

	@OneToOne
	@JoinColumn(name = "id_zona")
	private ZonasModel zona;
	
//	@OneToOne
//	@JoinColumn(name = "id_estado_trabajador")
//	private EstadoTrabajadoresModel estadoTrabajador;
	
	@OneToOne
	@JoinColumn(name = "id_empresa")
	private EmpresasModel empresa;
	
	@OneToOne
	@JoinColumn(name = "id_convenio")
	private ConveniosAplicablesModel convenio;

	public DeclaradosModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "DeclaradosModel [idDeclarado=" + idDeclarado + ", cuit=" + cuit + ", cuil=" + cuil + ", nombres="
				+ nombres + ", fechaIngreso=" + fechaIngreso + ", fechaEgreso=" + fechaEgreso + ", sueldo=" + sueldo
				+ ", localidadNombre=" + localidadNombre + ", estadoBaja=" + estadoBaja + ", fechaBaja=" + fechaBaja
				+ ", fecha=" + fecha + ", jornadaReducida=" + jornadaReducida + ", ingresoAyudante=" + ingresoAyudante
				+ ", mes=" + mes + ", anio=" + anio + ", rectificativa=" + rectificativa + ", montoSac=" + montoSac
				+ ", licencia=" + licencia + ", afiliadoFederacion=" + afiliadoObraSocial + ", observaciones="
				+ observaciones + ", cantidadDiasTrabajados=" + cantidadDiasTrabajados + ", nombreArchivo="
				+ nombreArchivo + ", categoria=" + categoria + ", localidad=" + localidad + ", sindicato=" + sindicato
				+ ", zona=" + zona + ", empresa=" + empresa + ", convenio=" + convenio + "]";
	}

	public int getIdDeclarado() {
		return idDeclarado;
	}

	public void setIdDeclarado(int idDeclarado) {
		this.idDeclarado = idDeclarado;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
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

	public String getLocalidadNombre() {
		return localidadNombre;
	}

	public void setLocalidadNombre(String localidadNombre) {
		this.localidadNombre = localidadNombre;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getJornadaReducida() {
		return jornadaReducida;
	}

	public void setJornadaReducida(String jornadaReducida) {
		this.jornadaReducida = jornadaReducida;
	}

	public String getIngresoAyudante() {
		return ingresoAyudante;
	}

	public void setIngresoAyudante(String ingresoAyudante) {
		this.ingresoAyudante = ingresoAyudante;
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

	public boolean isafiliadoObraSocial() {
		return afiliadoObraSocial;
	}

	public void setAfiliadoFederacion(boolean afiliadoFederacion) {
		this.afiliadoObraSocial = afiliadoFederacion;
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

	public LocalidadModel getLocalidad() {
		return localidad;
	}

	public void setLocalidad(LocalidadModel localidad) {
		this.localidad = localidad;
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

	public ConveniosAplicablesModel getConvenio() {
		return convenio;
	}

	public void setConvenio(ConveniosAplicablesModel convenio) {
		this.convenio = convenio;
	}

	
}
