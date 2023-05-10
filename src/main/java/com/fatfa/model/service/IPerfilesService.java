package com.fatfa.model.service;

import java.util.List;
import java.util.Map;

import com.fatfa.model.entity.PerfilesModel;

public interface IPerfilesService {

	/**
	 * @author CodeBluePeru
	 * @apiNote LISTA TODO LOS PERFILES
	 * @return
	 */
	List<PerfilesModel> srvListaPerfilesAll();

	/**
	 * @author CodeBluePeru
	 * @apiNote PERMITE CREAR UN NUEVO PERFIL
	 * @param perfil
	 * @return
	 */
	Map<String, Object> srvGuardarNuevoPerfil(PerfilesModel perfil);

	/**
	 * @author CodeBluePeru
	 * @apiNote PERMITE BUSCAR UN PERFIL SEGUN SU CLAVE PRIMARIA
	 * @param idperfil
	 * @return
	 */
	PerfilesModel srvBuscarPerfilId(int idperfil);

	/**
	 * @author CodeBluePeru
	 * @apiNote NOS PERMITIRA ACTUALIZAR UN PERFIL YA EXISTENTE
	 * @param perfil
	 * @param idPerfil
	 * @return
	 */
	Map<String, Object> srvActualizarPerfil(PerfilesModel perfil, int idPerfil);

	/**
	 * @author CodeBluePeru
	 * @apiNote NOS PERMITE BUSCAR UN PERFIL EXISTENTE Y ELIMINARLO
	 * @param idPerfil
	 * @return
	 */
	Map<String, Object> srvEliminarPerfil(int idPerfil);

}
