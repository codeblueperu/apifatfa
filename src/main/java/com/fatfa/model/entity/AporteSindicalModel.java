package com.fatfa.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_aporte_sindical")
public class AporteSindicalModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_aporte", nullable = false)
	private int idAporte ;
	
	@Column(name = "nombre_aporte", length = 60)
	private String nombreAporte;
	
	
}
