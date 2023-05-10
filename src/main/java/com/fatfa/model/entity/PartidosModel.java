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
@Table(name = "tb_partidos")
public class PartidosModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "id_partido", nullable = false)
	private int idPartido ;
	
	@Column(name = "nombre", length = 70, nullable = false)
	private String nombre;
	
	@Column(name = "codigo_indec", length = 10, nullable = false)
    private String codigoIndec;
	
	@OneToOne
	@JoinColumn(name="id_provincia", nullable = false)
	private ProvinciasModel provincia;

	public PartidosModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PartidosModel(int idPartido, String nombre, String codigoIndec, ProvinciasModel provincia) {
		super();
		this.idPartido = idPartido;
		this.nombre = nombre;
		this.codigoIndec = codigoIndec;
		this.provincia = provincia;
	}

	public int getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(int idPartido) {
		this.idPartido = idPartido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigoIndec() {
		return codigoIndec;
	}

	public void setCodigoIndec(String codigoIndec) {
		this.codigoIndec = codigoIndec;
	}

	public ProvinciasModel getProvincia() {
		return provincia;
	}

	public void setProvincia(ProvinciasModel provincia) {
		this.provincia = provincia;
	}

	@Override
	public String toString() {
		return "PartidosModel [idPartido=" + idPartido + ", nombre=" + nombre + ", codigoIndec=" + codigoIndec
				+ ", provincia=" + provincia + "]";
	}    
}
