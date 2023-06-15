package com.fatfa.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_categorias")
public class CategoriasModelo {
	
	@Id
	@Column(name = "id_categoria", length = 1, nullable = false)
	private String  idCategoria ;
	
	@Column(name = "categoria", length = 50, nullable = false)
	private String categoria;
	
	@Column(nullable = false, columnDefinition = "BIT default 1")
	private boolean estado;

	public CategoriasModelo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoriasModelo(String idCategoria, String categoria, boolean estado) {
		super();
		this.idCategoria = idCategoria;
		this.categoria = categoria;
		this.estado = estado;
	}
	public CategoriasModelo(String idCategoria) {
		super();
		this.idCategoria = idCategoria;
	}
	public String getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getCategoria() {
		return categoria.toUpperCase();
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
