package com.fatfa.model.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fatfa.model.entity.TransferenciaModel;

public interface ITransferenciaService {

	/**
	 * @author CodeBluePeru
	 * @apiNote PERMITE AGREGAR UNA NUEVA TRANSFERENCIA
	 * @return
	 */
	List<TransferenciaModel> srvListarTransaferencia(int idEmpresa, String estado, Date fecha);
	/**
	 * @author CodeBluePeru
	 * @apiNote PERMITE AGREGAR UNA NUEVA TRANSFERENCIA
	 * @param datos
	 * @return
	 */
	Map<String, Object> srvAgregarTransferencia(TransferenciaModel datos);
	
	/**
	 * @author CodeBluePeru
	 * @apiNote BUSCA LOS JUSTIFICATES DE TRANSFERENCIA
	 * @param idTransferencia
	 * @return
	 */
	TransferenciaModel srvBuscraComprobante(Integer idTransferencia);
}
