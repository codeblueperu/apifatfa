package com.fatfa.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_correlativos_documentos_fatfa")
public class CorrelativosDocumentosFatfa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_correlativo")
	private int idCorrelativo;
	
	private int correlativo;
	
	@Column(name = "descripcion_correlativo")
	private String descripcionCorrelativo;
	
	@Column(name = "numero_caracteres")
	private int numeroCaracteres;

	public CorrelativosDocumentosFatfa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CorrelativosDocumentosFatfa(int idCorrelativo, int correlativo, String descripcionCorrelativo,
			int numeroCaracteres) {
		super();
		this.idCorrelativo = idCorrelativo;
		this.correlativo = correlativo;
		this.descripcionCorrelativo = descripcionCorrelativo;
		this.numeroCaracteres = numeroCaracteres;
	}

	public int getIdCorrelativo() {
		return idCorrelativo;
	}

	public void setIdCorrelativo(int idCorrelativo) {
		this.idCorrelativo = idCorrelativo;
	}

	public int getCorrelativo() {
		return correlativo;
	}

	public void setCorrelativo(int correlativo) {
		this.correlativo = correlativo;
	}

	public String getDescripcionCorrelativo() {
		return descripcionCorrelativo;
	}

	public void setDescripcionCorrelativo(String descripcionCorrelativo) {
		this.descripcionCorrelativo = descripcionCorrelativo;
	}

	public int getNumeroCaracteres() {
		return numeroCaracteres;
	}

	public void setNumeroCaracteres(int numeroCaracteres) {
		this.numeroCaracteres = numeroCaracteres;
	}

	@Override
	public String toString() {
		return "CorrelativosDocumentosFatfa [idCorrelativo=" + idCorrelativo + ", correlativo=" + correlativo
				+ ", descripcionCorrelativo=" + descripcionCorrelativo + ", numeroCaracteres=" + numeroCaracteres + "]";
	}
}
