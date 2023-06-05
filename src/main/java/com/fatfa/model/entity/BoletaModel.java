package com.fatfa.model.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="tb_boleta")
public class BoletaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_boleta", nullable = false)
	private int idBoleta;
	
	@OneToOne
	@JoinColumn(name = "id_empresa")
	private EmpresasModel empresa;
	
	@NotBlank(message = "ESTE CAMPO ES REQUERIDO")
	@Column(length = 2, nullable = false)
	private String mes;
	
	@NotBlank(message = "ESTE CAMPO ES REQUERIDO")
	@Column(length = 4, nullable = false)
	private String anio;
	
	@OneToOne
	@JoinColumn(name = "id_banco")
	private BancosModel banco;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",locale = "es-PE", timezone = "America/Lima")
	@Column(name = "fecha_primer_vencimiento", nullable = false)
	private Date fechaPrimerVencimiento;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",locale = "es-PE", timezone = "America/Lima")
	@Column(name = "fecha_probable_pago", nullable = false)
	private Date fechaProbablePago;
		
	@Column(name = "subtotal", nullable = false, columnDefinition = "DECIMAL(18,2)")
	private Double subtotal;
	
	@Column(name = "intereces", nullable = false, columnDefinition = "DECIMAL(18,2)")
	private Double intereces;
	
	@Column(name = "importe_total", nullable = false, columnDefinition = "DECIMAL(18,2)")
	private Double importeTotal;
	
	@Column(name = "codigo_barras", nullable = false, length = 200)
	private String codigoBarras;
	
	@OneToOne
	@JoinColumn(name = "id_estado_pago")
	private EstadoPagoModel estaoPago;
	
	@Column(nullable = false, length = 100)
	private String archivo;
	
	@OneToOne
	@JoinColumn(name = "id_aporte")
	private AporteSindicalModel aporteSindical;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_detalle_aporte")
	private DetalleBoletaConceptoModel detalleConcepto;

	public BoletaModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoletaModel(int idBoleta, EmpresasModel empresa, @NotBlank(message = "ESTE CAMPO ES REQUERIDO") String mes,
			@NotBlank(message = "ESTE CAMPO ES REQUERIDO") String anio, BancosModel banco, Date fechaPrimerVencimiento,
			Date fechaProbablePago, Double subtotal, Double intereces, Double importeTotal, String codigoBarras,
			EstadoPagoModel estaoPago, String archivo, AporteSindicalModel aporteSindical,
			DetalleBoletaConceptoModel detalleConcepto) {
		super();
		this.idBoleta = idBoleta;
		this.empresa = empresa;
		this.mes = mes;
		this.anio = anio;
		this.banco = banco;
		this.fechaPrimerVencimiento = fechaPrimerVencimiento;
		this.fechaProbablePago = fechaProbablePago;
		this.subtotal = subtotal;
		this.intereces = intereces;
		this.importeTotal = importeTotal;
		this.codigoBarras = codigoBarras;
		this.estaoPago = estaoPago;
		this.archivo = archivo;
		this.aporteSindical = aporteSindical;
		this.detalleConcepto = detalleConcepto;
	}

	public int getIdBoleta() {
		return idBoleta;
	}

	public void setIdBoleta(int idBoleta) {
		this.idBoleta = idBoleta;
	}

	public EmpresasModel getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresasModel empresa) {
		this.empresa = empresa;
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

	public BancosModel getBanco() {
		return banco;
	}

	public void setBanco(BancosModel banco) {
		this.banco = banco;
	}

	public Date getFechaPrimerVencimiento() {
		return fechaPrimerVencimiento;
	}

	public void setFechaPrimerVencimiento(Date fechaPrimerVencimiento) {
		this.fechaPrimerVencimiento = fechaPrimerVencimiento;
	}

	public Date getFechaProbablePago() {
		return fechaProbablePago;
	}

	public void setFechaProbablePago(Date fechaProbablePago) {
		this.fechaProbablePago = fechaProbablePago;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Double getIntereces() {
		return intereces;
	}

	public void setIntereces(Double intereces) {
		this.intereces = intereces;
	}

	public Double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public EstadoPagoModel getEstaoPago() {
		return estaoPago;
	}

	public void setEstaoPago(EstadoPagoModel estaoPago) {
		this.estaoPago = estaoPago;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public AporteSindicalModel getAporteSindical() {
		return aporteSindical;
	}

	public void setAporteSindical(AporteSindicalModel aporteSindical) {
		this.aporteSindical = aporteSindical;
	}

	public DetalleBoletaConceptoModel getDetalleConcepto() {
		return detalleConcepto;
	}

	public void setDetalleConcepto(DetalleBoletaConceptoModel detalleConcepto) {
		this.detalleConcepto = detalleConcepto;
	}

	@Override
	public String toString() {
		return "BoletaModel [idBoleta=" + idBoleta + ", empresa=" + empresa + ", mes=" + mes + ", anio=" + anio
				+ ", banco=" + banco + ", fechaPrimerVencimiento=" + fechaPrimerVencimiento + ", fechaProbablePago="
				+ fechaProbablePago + ", subtotal=" + subtotal + ", intereces=" + intereces + ", importeTotal="
				+ importeTotal + ", codigoBarras=" + codigoBarras + ", estaoPago=" + estaoPago + ", archivo=" + archivo
				+ ", aporteSindical=" + aporteSindical + ", detalleConcepto=" + detalleConcepto + "]";
	}
}
