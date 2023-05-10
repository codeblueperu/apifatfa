package com.fatfa.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_usuarios_empresa")
public class UsuariosEmpresaModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id ;
	
	@OneToOne
	@JoinColumn(name = "id_empresa")
	private EmpresasModel empresa;
	
	@OneToOne
	@JoinColumn(name = "id_usuario")
	private UsuarioModel usuario;

	public UsuariosEmpresaModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsuariosEmpresaModel(int id, EmpresasModel empresa, UsuarioModel usuario) {
		super();
		this.id = id;
		this.empresa = empresa;
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EmpresasModel getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresasModel empresa) {
		this.empresa = empresa;
	}

	public UsuarioModel getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "UsuariosEmpresaModel [id=" + id + ", empresa=" + empresa + ", usuario=" + usuario + "]";
	}
}
