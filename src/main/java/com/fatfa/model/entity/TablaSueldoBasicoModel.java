package com.fatfa.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_tabla_sueldo_basico")
public class TablaSueldoBasicoModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_sueldo_basico")
	private int idSueldoBasico;
	
	@Column(length = 4, nullable = false)
	private String periodo;
	
	@Column(name = "id_categoria", nullable = false, length = 1)
	private String idCategoria;
	
	@Column(name = "sueldo_basico", nullable = false)
	private double sueldoBasico;
	
	@Column(nullable = false, columnDefinition = "BIT default 1")
	private boolean vigencia;

	public TablaSueldoBasicoModel() {
		super();
	}

	public TablaSueldoBasicoModel(int idSueldoBasico, String periodo, String idCategoria, double sueldoBasico,
			boolean vigencia) {
		super();
		this.idSueldoBasico = idSueldoBasico;
		this.periodo = periodo;
		this.idCategoria = idCategoria;
		this.sueldoBasico = sueldoBasico;
		this.vigencia = vigencia;
	}

	public int getIdSueldoBasico() {
		return idSueldoBasico;
	}

	public void setIdSueldoBasico(int idSueldoBasico) {
		this.idSueldoBasico = idSueldoBasico;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}

	public double getSueldoBasico() {
		return sueldoBasico;
	}

	public void setSueldoBasico(double sueldoBasico) {
		this.sueldoBasico = sueldoBasico;
	}

	public boolean isVigencia() {
		return vigencia;
	}

	public void setVigencia(boolean vigencia) {
		this.vigencia = vigencia;
	}

	@Override
	public String toString() {
		return "TablaSueldoBasicoModel [idSueldoBasico=" + idSueldoBasico + ", periodo=" + periodo + ", idCategoria="
				+ idCategoria + ", sueldoBasico=" + sueldoBasico + ", vigencia=" + vigencia + "]";
	}
}
