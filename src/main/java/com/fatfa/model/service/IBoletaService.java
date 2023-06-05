package com.fatfa.model.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fatfa.model.entity.BoletaModel;
import com.fatfa.model.entity.DetalleBoletaConceptoModel;

public interface IBoletaService {

	/**
	 * @author CodeBluePeru
	 * @param idEmpresa
	 * @param anio
	 * @param mes
	 * @param fechaPosiblePago
	 * @return
	 */
	DetalleBoletaConceptoModel onCalcularMontoBoleta(int idEmpresa, String anio, String mes, int totalDiasInteres);

	/**
	 * @author SOPORTE
	 * @param idNomina
	 * @param idempresa
	 * @param anio
	 * @param mes
	 * @return
	 */
	double onObtenerRemuneracionIntegral(int idNomina);

	/**
	 * @author CodeBluePeru
	 * @apiNote GUARDAR DATOS BOLETA PAGO
	 * @param dataBoleta
	 */
	void onGenerarTalonBoletaPago(BoletaModel dataBoleta, HttpServletRequest request, HttpServletResponse response);


	/**
	 * @author SOPORTE
	 * @apiNote EMPRIMIR REPORTE BOLETA SEGUN SEA BANCO
	 * @param idBoleta
	 * @param request
	 * @param response
	 */
	void onGenerarBoleta(int idBoleta,String nameFile, HttpServletRequest request, HttpServletResponse response);


}
