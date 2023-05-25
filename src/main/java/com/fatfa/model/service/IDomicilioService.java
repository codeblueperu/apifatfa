package com.fatfa.model.service;

import java.util.List;
import java.util.Map;

import com.fatfa.model.entity.DomicilioModel;

public interface IDomicilioService {

	/**
	 * @author CodeBluePeru
	 * @apiNote PERMITE AGREGAR UN NUEVO DOMICILIO DE EMPRESA
	 * @param domicilio
	 * @return
	 */
	Map<String, Object> srvAgregarDomicilio(List<DomicilioModel> domicilio);
}
