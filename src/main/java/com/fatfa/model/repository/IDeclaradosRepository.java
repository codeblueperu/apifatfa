package com.fatfa.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.DeclaradosModel;
import com.fatfa.model.entity.EmpresasModel;

@Repository
public interface IDeclaradosRepository extends JpaRepository<DeclaradosModel, Integer> {

	/**
	 * @author CodeBluePeru
	 * @apiNote BUSCAR DECLARADOS  POR CODIGO DE EMPRESA, MES, ANIO Y RECTIFICATIVA DE LA BASE DE DATOS
	 * @param empresa
	 * @param mes
	 * @param anio
	 * @param rectificativa
	 * @return
	 */
	List<DeclaradosModel> findByEmpresaAndMesAndAnioAndRectificativa(EmpresasModel empresa, String mes, String anio, Integer rectificativa);
}
