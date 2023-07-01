package com.fatfa.model.service;

import java.util.List;
import java.util.Map;

import com.fatfa.model.entity.ConveniosAplicablesModel;

public interface ConveniosAplicablesService {
	/**
	 * @author SOPORTE
	 * @apiNote GUARDAR CONVENIOS
	 * @param datos
	 * @return
	 */
	Map<String, Object> srvCrearEditarConvenios(ConveniosAplicablesModel datos);
	
	/**
	 * @author SOPORTE
	 * @apiNote LISTA TODO LOS CONVENIOS
	 * @return
	 */
	List<ConveniosAplicablesModel> srvListaConvenios();
	
	/**
	 * @author SOPORTE
	 * @apiNote BUSCAR CONVENIOS POR ID
	 * @param idconvenio
	 * @return
	 */
	ConveniosAplicablesModel srvBuscarConveniosID(int idconvenio);
	
	/**
	 * @author SOPORTE
	 * @apiNote ELIMINAR CONVENIOS ID
	 * @param idconvenio
	 * @return
	 */
	Map<String, Object> srvEliminarConveniosID(int idconvenio);
	
	/**
	 * @author SOPORTE
	 * @apiNote ACTUALIZAR ESTADO CONVENIOS
	 * @param idconvenio
	 * @return
	 */
	Map<String, Object> srvEstadoConvenios(int idconvenio);
}
