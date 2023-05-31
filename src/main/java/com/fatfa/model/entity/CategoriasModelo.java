package com.fatfa.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_categorias")
public class CategoriasModelo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_categoria")
	private int idCategoria ;
	
	@Column(name = "categoria", length = 50, nullable = false)
	private String categoria;
	
	@Column(nullable = false, columnDefinition = "BIT default 1")
	private boolean estado;

	public CategoriasModelo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoriasModelo(int idCategoria, String categoria, boolean estado) {
		super();
		this.idCategoria = idCategoria;
		this.categoria = categoria;
		this.estado = estado;
	}
	public CategoriasModelo(int idCategoria) {
		super();
		this.idCategoria = idCategoria;
	}
	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "CategoriasModelo [idCategoria=" + idCategoria + ", categoria=" + categoria + ", estado=" + estado + "]";
	}

}
