package com.fatfa.model.service;

import java.util.List;
import java.util.Map;

import com.fatfa.model.entity.SolicitudesUsuarioEmpresaModel;

public interface ISolicitudUsuarioEmpresaService {
	
	/**
	 * @author CodeBluePeru
	 * @apiNote BUSCAR SOLICITUDES POR EMPRESA O ESTADO
	 * @param idEmpresa
	 * @param estadoSolicitud
	 * @return
	 */
	List<SolicitudesUsuarioEmpresaModel> srvListaSolicitudesUsuarioEmpresa(int idEmpresa, String estadoSolicitud);

	/**
	 * @author CodeBluePeru
	 * @apiNote CREAR Y ACTUALIZAR UNA SAOLICITUD DE USUARIO
	 * @param solicitud
	 * @return
	 */
	Map<String, Object> srvCreateUpdateSolicitudUsuarioEmpresa(SolicitudesUsuarioEmpresaModel solicitud);
	
	/**
	 * @author CodeBluePeru
	 * @apiNote BUSCAR DATOS DE UN SAOLICITUD POR SU ID
	 * @param idSolicitud
	 * @return
	 */
	SolicitudesUsuarioEmpresaModel srvBuscarSolicitudUsuarioEmpresaID(int idSolicitud);
	
	/**
	 * @author CodeBluePeru
	 * @apiNote RECHARA SOLICITUD POR ALGUN MOTIVO
	 * @param idSolicitud
	 * @param observacion
	 * @return
	 */
	Map<String, Object> srvAnularSolicitudUsuarioEmpresa(int idSolicitud, String observacion);
	
	/**
	 * @author CodeBluePeru
	 * @apiNote PERMITE APROBAR UNA SOLICITUD DE USUARIO EMPRESA
	 * @param idSolicitud
	 * @return
	 */
	Map<String, Object> srvAprobarSolicitudUsuarioEmpresa(int idSolicitud);
}
