package com.fatfa.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.NominasModel;
import com.fatfa.model.entity.EmpresasModel;

@Repository
public interface IDeclaradosRepository extends JpaRepository<NominasModel, Integer> {

	/**
	 * @author CodeBluePeru
	 * @apiNote BUSCAR DECLARADOS  POR CODIGO DE EMPRESA, MES, ANIO Y RECTIFICATIVA DE LA BASE DE DATOS
	 * @param empresa
	 * @param mes
	 * @param anio
	 * @param rectificativa
	 * @return
	 */
	List<NominasModel> findByEmpresaAndMesAndAnioAndRectificativa(EmpresasModel empresa, String mes, String anio, Integer rectificativa);
	
	@Query("SELECT COUNT(*) FROM NominasModel n WHERE n.empresa.idEmpresa = ?1 AND n.anio = ?2 AND n.mes = ?3")
	int findContarItemRectificativaEmpresaAnioMes(int id_empresa,String anio,String  mes);
}
