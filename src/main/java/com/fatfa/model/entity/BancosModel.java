package com.fatfa.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_bancos")
public class BancosModel {

	@Id
	@Column(name = "id_banco", length = 4, nullable = false)
	private String idBanco ;
	
	@Column(name = "banco", length = 80, nullable = false)
	private String banco;
	
	@Column(nullable = false, columnDefinition = "BIT default 1")
	private boolean estado;
	
	@Column(name = "identificador", length = 4, nullable = false)
	private String identificador ;

	public BancosModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BancosModel(String idBanco, String banco, boolean estado, String identificador) {
		super();
		this.idBanco = idBanco;
		this.banco = banco;
		this.estado = estado;
		this.identificador = identificador;
	}

	public String getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(String idBanco) {
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

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	@Override
	public String toString() {
		return "BancosModel [idBanco=" + idBanco + ", banco=" + banco + ", estado=" + estado + ", identificador="
				+ identificador + "]";
	}
}
