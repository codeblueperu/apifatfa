package com.fatfa.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_bancos")
public class BancosModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_banco", nullable = false)
	private int idBanco ;
	
	@Column(name = "banco", length = 80, nullable = false)
	private String banco;
	
	@Column(nullable = false, columnDefinition = "BIT default 1")
	private boolean estado;

	public BancosModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BancosModel(int idBanco, String banco, boolean estado) {
		super();
		this.idBanco = idBanco;
		this.banco = banco;
		this.estado = estado;
	}

	public int getidBanco() {
		return idBanco;
	}

	public void setidBanco(int idBanco) {
		this.idBanco = idBanco;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "BancosModel [idBanco=" + idBanco + ", banco=" + banco + ", estado=" + estado + "]";
	}
}
