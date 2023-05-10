package com.fatfa.model.service;

import java.util.List;
import java.util.Map;

import com.fatfa.model.entity.UsuarioModel;

public interface IUsersService {

	/**
	 * @author CodeBluePeru
	 * @apiNote BUSCAR CUENTA EMAIL 
	 * @param email
	 * @return
	 */
	UsuarioModel srvBuscarUsuarioEmail(String email);
	
	/**
	 * @author CodeBluePeru
	 * @apiNote LISTA TODO LOS USUARIO DE LA BASE DE DATOS
	 * @return
	 */
	List<UsuarioModel> srvListaUsuario();
	
	/**
	 * @author CodeBluePeru
	 * @apiNote PERMITE CREAR UN NUEVO USUARIO
	 * @param usuario
	 * @return
	 */
	Map<String, Object> srvCrearUsuario(UsuarioModel usuario);
	
	/**
	 * @author CodeBluePeru
	 * @apiNote PERMITE BUSCAR UN USUARIO POR SU ID
	 * @param idUsuario
	 * @return
	 */
	UsuarioModel srvBuscarusuarioID(int idUsuario);
	
	/**
	 * @author CodeBluePeru
	 * @apiNote PERMITE ACTUALIZAR UN USUARIO SEGUN SU ID
	 * @param usuario
	 * @param idUsuario
	 * @return
	 */
	Map<String, Object> srvActualizarUsuario(UsuarioModel usuario, int idUsuario);
	
	/**
	 * @author CodeBluePeru
	 * @apiNote PERMITE ELIMINAR UN USUARIO SEGUN SU ID
	 * @param idUsuario
	 * @return
	 */
	Map<String, Object> srvEiminarUsuario(int idUsuario);
}
