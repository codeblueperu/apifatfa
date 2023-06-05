package com.fatfa.model.service;


public interface IBancosService {

	/**
	 * @author SOPORTE
	 * @apiNote BANCO DE LA NACION ARGENTINA
	 * @param idBoleta
	 * @return
	 */
	String onGeneraCodigigoBarraBancoNacion(int idBoleta);
	
	/**
	 * @author SOPORTE
	 * @apiNote PAGO FACIL ARGENTINA
	 * @param idBoleta
	 * @return
	 */
	String onGeneraCodigoBarraPagoFacil(int idBoleta);
	
	/**
	 * @author SOPORTE
	 * @param fechaOne
	 * @param fechaTwo
	 * @return
	 */
	int onCalcularDiferenciaFechas(String fechaVencimiento);
}
