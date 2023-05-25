package com.fatfa.model.service;

import java.util.List;
import java.util.Map;

import com.fatfa.model.entity.DomicilioModel;
import com.fatfa.model.entity.EmpresasModel;
import com.fatfa.model.entity.ResponsableDDJJModel;
import com.fatfa.model.entity.ResponsableRRHHModel;

public interface IEmpresaService {

	/**
	 * @author CodeBluePeru
	 * @apiNote LISTA TODAS LAS EMPRESAS DE LA BASE DE DATOS
	 * @return
	 */
	List<EmpresasModel> srvListaEmpresas();
	
	/**
	 * @author CodeBluePeru
	 * @apiNote PERMITE ELIMINAR UNA EMPRESA SEGUN SU ID
	 * @param idEmpresa
	 * @return
	 */
	
	Map<String, Object> srvEliminarEmpresas(int idEmpresa);
	
	/**
	 * @author CodeBluePeru
	 * @apiNote PERMITE BUSCAR UNA EMPRESA POR SU ID
	 * @param idEmpresa
	 * @return
	 */
	EmpresasModel srvBuscarEmpresaID(int idEmpresa);
	
	/**
	 * @author CodeBluePeru
	 * @apiNote BUSCAR CUENTA EMAIL 
	 * @param nombre
	 * @return
	 */
	EmpresasModel srvBuscarEmpresaNombre(String nombre, String razonsocial);
	/**
	 * @author CodeBluePeru
	 * @apiNote PERMITE AGREGAR UNA NUEVA EMPRESA y DOMICILIO
	 * @param empresa
	 * @return
	 */
	Map<String, Object> srvAgregarEmpresa(EmpresasModel empresa, List<DomicilioModel> domicilio, ResponsableDDJJModel responsableDDJJ, ResponsableRRHHModel responsableRRHH);
}
