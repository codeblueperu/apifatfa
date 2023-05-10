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
	
	@Column(name = "localidad_trabajador", length = 70, nullable = false)
	private String localidadTrabajador;
	
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
	
	@Column(name="sueldo_remunerativo",nullable = true, columnDefinition = "decimal(18,2)")
	private double sueldoRemunerativo;
	
	@Column(name="monto_sac",nullable = true, columnDefinition = "decimal(18,2)")
	private double montoSac ;
	
	@Column(name="monto_vacaciones",nullable = true, columnDefinition = "decimal(18,2)")
	private double montoVacaciones;
	
	@Column(nullable = true, columnDefinition = "BIT")
	private boolean licencia;
	
	@Column(name="afiliado_federacion",nullable = true, columnDefinition = "BIT")
	private boolean afiliadoFederacion;
	
	@Column(name="afiliado_obra_social",nullable = true, columnDefinition = "BIT")
	private boolean afiliadoObraSocial;
	
	@Column(nullable = true, columnDefinition = "TEXT")
	private String observaciones;
	
	@Column(name = "cantidad_dias_trabajados", nullable = true)
	private int cantidadDiasTrabajados;
	
	@Column(name = "cantidad_dias_vacaciones", nullable = true)
	private int cantidadDiasVacaciones;
	
	@Column(name = "sueldo_no_remunerativo", nullable = true, columnDefinition = "decimal(18,2)")
	private double sueldo_no_remunerativo ;
	
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
	
	@OneToOne
	@JoinColumn(name = "id_estado_trabajador")
	private EstadoTrabajadoresModel estadoTrabajador;

	public DeclaradosModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeclaradosModel(int idDeclarado, String cuit, String cuil, String nombres, Date fechaIngreso,
			Date fechaEgreso, double sueldo, String localidadNombre, boolean estadoBaja, Date fechaBaja, Date fecha,
			String localidadTrabajador, String jornadaReducida, String ingresoAyudante, String mes, String anio,
			int rectificativa, double sueldoRemunerativo, double montoSac, double montoVacaciones, boolean licencia,
			boolean afiliadoFederacion, boolean afiliadoObraSocial, String observaciones, int cantidadDiasTrabajados,
			int cantidadDiasVacaciones, double sueldo_no_remunerativo, String nombreArchivo, CategoriasModelo categoria,
			LocalidadModel localidad, SindicatosModel sindicato, ZonasModel zona,
			EstadoTrabajadoresModel estadoTrabajador) {
		super();
		this.idDeclarado = idDeclarado;
		this.cuit = cuit;
		this.cuil = cuil;
		this.nombres = nombres;
		this.fechaIngreso = fechaIngreso;
		this.fechaEgreso = fechaEgreso;
		this.sueldo = sueldo;
		this.localidadNombre = localidadNombre;
		this.estadoBaja = estadoBaja;
		this.fechaBaja = fechaBaja;
		this.fecha = fecha;
		this.localidadTrabajador = localidadTrabajador;
		this.jornadaReducida = jornadaReducida;
		this.ingresoAyudante = ingresoAyudante;
		this.mes = mes;
		this.anio = anio;
		this.rectificativa = rectificativa;
		this.sueldoRemunerativo = sueldoRemunerativo;
		this.montoSac = montoSac;
		this.montoVacaciones = montoVacaciones;
		this.licencia = licencia;
		this.afiliadoFederacion = afiliadoFederacion;
		this.afiliadoObraSocial = afiliadoObraSocial;
		this.observaciones = observaciones;
		this.cantidadDiasTrabajados = cantidadDiasTrabajados;
		this.cantidadDiasVacaciones = cantidadDiasVacaciones;
		this.sueldo_no_remunerativo = sueldo_no_remunerativo;
		this.nombreArchivo = nombreArchivo;
		this.categoria = categoria;
		this.localidad = localidad;
		this.sindicato = sindicato;
		this.zona = zona;
		this.estadoTrabajador = estadoTrabajador;
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

	public String getLocalidadTrabajador() {
		return localidadTrabajador;
	}

	public void setLocalidadTrabajador(String localidadTrabajador) {
		this.localidadTrabajador = localidadTrabajador;
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

	public double getSueldoRemunerativo() {
		return sueldoRemunerativo;
	}

	public void setSueldoRemunerativo(double sueldoRemunerativo) {
		this.sueldoRemunerativo = sueldoRemunerativo;
	}

	public double getMontoSac() {
		return montoSac;
	}

	public void setMontoSac(double montoSac) {
		this.montoSac = montoSac;
	}

	public double getMontoVacaciones() {
		return montoVacaciones;
	}

	public void setMontoVacaciones(double montoVacaciones) {
		this.montoVacaciones = montoVacaciones;
	}

	public boolean isLicencia() {
		return licencia;
	}

	public void setLicencia(boolean licencia) {
		this.licencia = licencia;
	}

	public boolean isAfiliadoFederacion() {
		return afiliadoFederacion;
	}

	public void setAfiliadoFederacion(boolean afiliadoFederacion) {
		this.afiliadoFederacion = afiliadoFederacion;
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

	public int getCantidadDiasVacaciones() {
		return cantidadDiasVacaciones;
	}

	public void setCantidadDiasVacaciones(int cantidadDiasVacaciones) {
		this.cantidadDiasVacaciones = cantidadDiasVacaciones;
	}

	public double getSueldo_no_remunerativo() {
		return sueldo_no_remunerativo;
	}

	public void setSueldo_no_remunerativo(double sueldo_no_remunerativo) {
		this.sueldo_no_remunerativo = sueldo_no_remunerativo;
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

	public EstadoTrabajadoresModel getEstadoTrabajador() {
		return estadoTrabajador;
	}

	public void setEstadoTrabajador(EstadoTrabajadoresModel estadoTrabajador) {
		this.estadoTrabajador = estadoTrabajador;
	}

	@Override
	public String toString() {
		return "DeclaradosModel [idDeclarado=" + idDeclarado + ", cuit=" + cuit + ", cuil=" + cuil + ", nombres="
				+ nombres + ", fechaIngreso=" + fechaIngreso + ", fechaEgreso=" + fechaEgreso + ", sueldo=" + sueldo
				+ ", localidadNombre=" + localidadNombre + ", estadoBaja=" + estadoBaja + ", fechaBaja=" + fechaBaja
				+ ", fecha=" + fecha + ", localidadTrabajador=" + localidadTrabajador + ", jornadaReducida="
				+ jornadaReducida + ", ingresoAyudante=" + ingresoAyudante + ", mes=" + mes + ", anio=" + anio
				+ ", rectificativa=" + rectificativa + ", sueldoRemunerativo=" + sueldoRemunerativo + ", montoSac="
				+ montoSac + ", montoVacaciones=" + montoVacaciones + ", licencia=" + licencia + ", afiliadoFederacion="
				+ afiliadoFederacion + ", afiliadoObraSocial=" + afiliadoObraSocial + ", observaciones=" + observaciones
				+ ", cantidadDiasTrabajados=" + cantidadDiasTrabajados + ", cantidadDiasVacaciones="
				+ cantidadDiasVacaciones + ", sueldo_no_remunerativo=" + sueldo_no_remunerativo + ", nombreArchivo="
				+ nombreArchivo + ", categoria=" + categoria + ", localidad=" + localidad + ", sindicato=" + sindicato
				+ ", zona=" + zona + ", estadoTrabajador=" + estadoTrabajador + "]";
	}
}
