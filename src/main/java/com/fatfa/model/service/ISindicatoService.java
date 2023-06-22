package com.fatfa.model.service;

import java.util.List;
import java.util.Map;

import com.fatfa.model.entity.SindicatosModel;

public interface ISindicatoService {

	/**
	 * @author SOPORTE
	 * @apiNote GUARDAR NUEVO SINDICATO
	 * @param sindicato
	 * @return
	 */
	Map<String, Object> srvCrearEditarSindicato(SindicatosModel sindicato);
	
	/**
	 * @author SOPORTE
	 * @apiNote LISTA TODO LOS SINDICATOS
	 * @return
	 */
	List<SindicatosModel> srvListaSindicatos();
	
	/**
	 * @author SOPORTE
	 * @apiNote BUSCAR SINDICATO POR ID
	 * @param idSindicato
	 * @return
	 */
	SindicatosModel srvBuscarSindicatoID(int idSindicato);
	
	/**
	 * @author SOPORTE
	 * @apiNote ELIMINAR SINDICATO ID
	 * @param idSindicato
	 * @return
	 */
	Map<String, Object> srvEliminarSindicatoID(int idSindicato);
	
	/**
	 * @author SOPORTE
	 * @apiNote ACTUALIZAR ESTADO SINDICATO
	 * @param idSindicato
	 * @return
	 */
	Map<String, Object> srvEstadoSindicato(int idSindicato);
}
