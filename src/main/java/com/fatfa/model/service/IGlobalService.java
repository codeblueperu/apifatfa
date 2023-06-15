package com.fatfa.model.service;

import java.util.List;

import com.fatfa.model.dto.MatrizPeriodoIntersDTO;

public interface IGlobalService {

	/**
	 * @author SOPORTE
	 * @apiNote OBETENER LA DIFERENCIA DE DIAS
	 * @param fechaInio
	 * @param fechaVencimiento
	 * @return
	 */
	int onCalcularDiferenciaFechas(String fechaInio, String fechaVencimiento);
	
	/**
	 * @author SOPORTE
	 * @apiNote OBTENER EL VALOR INTERES SEGUN SEA EL TIEMPO
	 * @param tipoInteres
	 * @param fechaInicioAAAAMM
	 * @param fechaFinAAAAMM
	 * @return
	 */
	 double onObetenerValorInteres(int tipoInteres, String fechaInicioAAAAMM, String fechaFinAAAAMM);
	 
	 /**
	  * @author SOPORTE
	  * @apiNote OBTENER LA VALOR INTEGRAL SEGUN SEA LA NOMINA DEL TRABAJADOR
	  * @param idNomina
	  * @return
	  */
	 double onObtenerRemuneracionIntegral(int idNomina, double sueldoBasico);
	 
	 /**
	  * @author SOPORTE
	  * @apiNote OBTENER EN LISTA LOS PERIODOS EN SECUENCIAL SEGUN LAS FECHA
	  * @return
	  */
	 List<MatrizPeriodoIntersDTO> onObtenerMatrizPerido();
	 

}
