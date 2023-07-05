package com.fatfa.model.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fatfa.model.entity.TransferenciaModel;

public interface ITransferenciaService {

	/**
	 * @author CodeBluePeru
	 * @apiNote LISTA TODAS LAS TRANSFERENCIAS SEGUN LOS PARAMETROS ENVIADOS
	 * @return
	 */
	List<TransferenciaModel> srvListarTransaferencia(int idEmpresa, int estado, Date fecha, Date fechaF);
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
	
	/**
	 * @author CodeBluePeru
	 * @apiNote ACTUALIZA EL ESTADO DE PAGO DE TRANSFERENCIAS
	 * @param idTransferencia
	 * @return
	 */
	Map<String, Object> srvUpdateTransferencias(int idTransferencias, int estadoPago);
}
