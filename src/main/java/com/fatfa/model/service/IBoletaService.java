package com.fatfa.model.service;

import com.fatfa.model.entity.BoletaModel;

public interface IBoletaService {


	/**
	 * @author SOPORTE
	 * @param idEmpresa
	 * @param anio
	 * @param mes
	 * @return
	 */
	BoletaModel onCalcularMontoBoleta(int idEmpresa, String anio, String mes);
	
	/**
	 * @author SOPORTE
	 * @param idNomina
	 * @param idempresa
	 * @param anio
	 * @param mes
	 * @return
	 */
	double onObtenerRemuneracionIntegral(int idNomina);
}
