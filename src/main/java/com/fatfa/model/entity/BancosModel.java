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
	private int id_banco ;
	
	@Column(name = "banco", length = 80, nullable = false)
	private String banco;
	
	@Column(nullable = false, columnDefinition = "BIT default 1")
	private boolean estado;

	public BancosModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BancosModel(int id_banco, String banco, boolean estado) {
		super();
		this.id_banco = id_banco;
		this.banco = banco;
		this.estado = estado;
	}

	public int getId_banco() {
		return id_banco;
	}

	public void setId_banco(int id_banco) {
		this.id_banco = id_banco;
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
		return "BancosModel [id_banco=" + id_banco + ", banco=" + banco + ", estado=" + estado + "]";
	}
}
