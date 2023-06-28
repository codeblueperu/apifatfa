package com.fatfa.model.service;

import java.util.List;
import java.util.Map;

import com.fatfa.model.entity.CategoriasModelo;

public interface ICategoriaService {

	/**
	 * @author SOPORTE
	 * @apiNote GUARDAR NUEVO CATEGORIA
	 * @param categoria
	 * @return
	 */
	Map<String, Object> srvCrearEditarCategoria(CategoriasModelo categoria);
	
	/**
	 * @author SOPORTE
	 * @apiNote LISTA TODO LAS CATEGORIA
	 * @return
	 */
	List<CategoriasModelo> srvListaCategoria();
	
	/**
	 * @author SOPORTE
	 * @apiNote BUSCAR CATEGORIA POR ID
	 * @param idCategoria
	 * @return
	 */
	CategoriasModelo srvBuscarCategoriaID(String idCategoria);
	
	/**
	 * @author SOPORTE
	 * @apiNote ELIMINAR CATEGORIA ID
	 * @param idCategoria
	 * @return
	 */
	Map<String, Object> srvEliminarCategoriaID(String idCategoria);
	
	/**
	 * @author SOPORTE
	 * @apiNote ACTUALIZAR ESTADO CATEGORIA
	 * @param idCategoria
	 * @return
	 */
	Map<String, Object> srvEstadoCategoria(String idCategoria);
}
