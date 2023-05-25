package com.fatfa.model.service;

import java.util.Map;

import com.fatfa.model.entity.ResponsableDDJJModel;
import com.fatfa.model.entity.ResponsableRRHHModel;

public interface IResponsablesService {

	/**
	 * @author CodeBluePeru
	 * @apiNote PERMITE AGREGAR UN NUEVO RESPONSABLE DDJJ DE EMPRESA
	 * @param empresa
	 * @return
	 */
	Map<String, Object> srvAgregarResponsableDDJJ(ResponsableDDJJModel responsableDDJJ);
	
	/**
	 * @author CodeBluePeru
	 * @apiNote PERMITE AGREGAR UN NUEVO RESPONSABLE RRHH DE EMPRESA
	 * @param empresa
	 * @return
	 */
	Map<String, Object> srvAgregarResponsableRRHH(ResponsableRRHHModel responsablerRHH);
}
