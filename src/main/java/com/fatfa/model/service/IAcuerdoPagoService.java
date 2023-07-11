package com.fatfa.model.service;

import java.util.HashMap;
import java.util.List;

import com.fatfa.model.entity.AcuerdosPagoModel;

public interface IAcuerdoPagoService {

	List<AcuerdosPagoModel> srvListaAcuerdosPago(int idEmpresa);
	
	HashMap<String, Object> srvCalcularDetalleCuotas(double capitalInicial, double tasainteres, int ncuotas);
}
