package com.fatfa.model.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fatfa.model.entity.DepositosModel;

public interface DepositoService {
	/**
	 * @author CodeBluePeru
	 * @apiNote LISTA TODAS LAS TRANSFERENCIAS SEGUN LOS PARAMETROS ENVIADOS
	 * @return
	 */
	DepositosModel srvBuscarDepositoID(int idDeposito);
	
	/**
	 * @author CodeBluePeru
	 * @apiNote LISTA TODOS LOS DEPOSITOS SEGUN LOS PARAMETROS ENVIADOS
	 * @return
	 */
	List<DepositosModel> srvfindByEmpresaIdEmpresaAndEstadoPagoIdEstadoPagoAndFechaPagoBetween(int empresa, int estadoPago, Date fecha, Date fechaF);
	
	/**
	 * @author CodeBluePeru
	 * @apiNote GUARDA NUEVO DEPOSITO EN LA BASE DE DATOS
	 * @return
	 */
	
	Map<String, Object> srvSaveDepositos(DepositosModel depositos);
	
	/**
	 * @author CodeBluePeru
	 * @apiNote ACTUALIZA ES ESTADO DE DEPOSITO 
	 * @return
	 */
	Map<String, Object> srvUpdateDepositos(int idDeposito, int estadoPago);
}
