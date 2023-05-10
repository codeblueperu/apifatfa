package com.fatfa.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_domicilio_empresas")
public class DomiciliosEmpresaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private boolean estado;

	@OneToOne()
	@JoinColumn(name = "id_empresa")
	private EmpresasModel empresa;

	@OneToOne()
	@JoinColumn(name = "id_domicilio")
	private DomicilioModel domicilio;

	public DomiciliosEmpresaModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DomiciliosEmpresaModel(int id, boolean estado, EmpresasModel empresa, DomicilioModel domicilio) {
		super();
		this.id = id;
		this.estado = estado;
		this.empresa = empresa;
		this.domicilio = domicilio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public EmpresasModel getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresasModel empresa) {
		this.empresa = empresa;
	}

	public DomicilioModel getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(DomicilioModel domicilio) {
		this.domicilio = domicilio;
	}

	@Override
	public String toString() {
		return "DomiciliosEmpresaModel [id=" + id + ", estado=" + estado + ", empresa=" + empresa + ", domicilio="
				+ domicilio + "]";
	}
}
