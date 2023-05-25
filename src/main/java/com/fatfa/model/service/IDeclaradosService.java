package com.fatfa.model.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fatfa.model.entity.DeclaradosModel;
import com.fatfa.model.entity.EmpresasModel;

public interface IDeclaradosService {

	/**
	 * @author CodeBluePeru
	 * @apiNote PERMITE AGREGAR UN NUEVO DECLARADO DE EMPRESA
	 * @param declarados
	 * @return
	 */
	Map<String, Object> srvAgregarDeclarados(DeclaradosModel declarados);
	
	/**
	 * @author CodeBluePeru
	 * @apiNote COPIA Y GUARDA LOS DECLARADOS DE LA EMPRESA
	 * @param declarados
	 * @return
	 */
	List<DeclaradosModel> srvCopiarDeclarados(List<DeclaradosModel> declarados);
	
	
	/**
	 * @author CodeBluePeru
	 * @apiNote BUSCAR DECLARADOS  POR CODIGO DE EMPRESA, MES, ANIO Y RECTIFICATIVA DE LA BASE DE DATOS
	 * @param empresa
	 * @param mes
	 * @param anio
	 * @param rectificativa
	 * @return
	 */
	List<DeclaradosModel> srvBuscarDeclarados(EmpresasModel empresa, String mes, String anio, Integer rectificativa);
	
	
}
