package com.fatfa.model.service;


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
	void onGenerarTalonBoletaPago(BoletaModel dataBoleta);

	/**
	 * @author CodeBluePeru
	 * @apiNote GENERAR CODIGO DE BARRA SEGUN EL TIPO DEL BANCO
	 * @param idBoleta
	 * @return
	 */
	String onGenerarCodigoBarraSegunTipoBanco(int idBoleta);
	
	BoletaModel prueba ();
	

}
