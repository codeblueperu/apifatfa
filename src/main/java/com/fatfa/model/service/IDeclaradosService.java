package com.fatfa.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.fatfa.model.entity.NominasModel;
import com.fatfa.model.entity.EmpresasModel;

public interface IDeclaradosService {

	/**
	 * @author CodeBluePeru
	 * @apiNote PERMITE AGREGAR UN NUEVO DECLARADO DE EMPRESA
	 * @param declarados
	 * @return
	 */
	Map<String, Object> srvAgregarDeclarados(NominasModel declarados);
	/**
	 * @author CodeBluePeru
	 * @param fileExcel
	 * @param id_empresa
	 * @param anio
	 * @param mes
	 * @param rectificativa
	 * @return
	 */
	Map<String, Object> srvGuardarNominaMasiva(MultipartFile fileExcel, int id_empresa, String anio, String mes,
			int rectificativa);
	
	/**
	 * @author CodeBluePeru
	 * @param id_empresa
	 * @param anio
	 * @param mes
	 * @return
	 */
	int srvObtenerelUltimoRectificativo(int id_empresa, String anio, String mes);
	
	/**
	 * @author CodeBluePeru
	 * @apiNote PERMITE AGREGAR UN NUEVO DECLARADO DE EMPRESA
	 * @param id
	 * @return
	 */
	NominasModel srvBuscarDeclaradosID(int id);
	
	
	/**
	 * @author CodeBluePeru
	 * @apiNote COPIA Y GUARDA LOS DECLARADOS DE LA EMPRESA
	 * @param declarados
	 * @return
	 */
	List<NominasModel> srvCopiarDeclarados(List<NominasModel> declarados);
	
	
	/**
	 * @author CodeBluePeru
	 * @apiNote BUSCAR DECLARADOS  POR CODIGO DE EMPRESA, MES, ANIO Y RECTIFICATIVA DE LA BASE DE DATOS
	 * @param empresa
	 * @param mes
	 * @param anio
	 * @param rectificativa
	 * @return
	 */
	List<NominasModel> srvBuscarDeclarados(EmpresasModel empresa, String mes, String anio, Integer rectificativa);
	
	/**
	 * @author CodeBluePeru
	 * @apiNote GUARDA EL ARCHIVO DE JUSTIFICANTE REDUCIDA EN EL WEBAPP
	 * @param file
	 * @return
	 */
	String saveFile(MultipartFile file);
	
}
