package com.fatfa.model.service;

import java.util.List;
import java.util.Map;

import com.fatfa.model.entity.ZonasModel;

public interface IZonaService {

	/**
	 * @author SOPORTE
	 * @apiNote GUARDAR ZONAS
	 * @param zonas
	 * @return
	 */
	Map<String, Object> srvCrearEditarZonas(ZonasModel zonas);
	
	/**
	 * @author SOPORTE
	 * @apiNote LISTA TODO LAS ZONAS
	 * @return
	 */
	List<ZonasModel> srvListaZonas();
	
	/**
	 * @author SOPORTE
	 * @apiNote BUSCAR ZONAS POR ID
	 * @param idzonas
	 * @return
	 */
	ZonasModel srvBuscarZonasID(int idzonas);
	
	/**
	 * @author SOPORTE
	 * @apiNote ELIMINAR ZONAS ID
	 * @param idzonas
	 * @return
	 */
	Map<String, Object> srvEliminarZonasID(int idzonas);
	
	/**
	 * @author SOPORTE
	 * @apiNote ACTUALIZAR ZONAS CATEGORIA
	 * @param idzonas
	 * @return
	 */
	Map<String, Object> srvEstadoZonas(int idzonas);
}
