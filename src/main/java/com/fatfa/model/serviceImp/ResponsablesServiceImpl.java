package com.fatfa.model.serviceImp;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatfa.model.entity.ResponsableDDJJModel;
import com.fatfa.model.entity.ResponsableRRHHModel;
import com.fatfa.model.repository.IResponsableDDJJRepository;
import com.fatfa.model.repository.IResponsableRRHHRepository;
import com.fatfa.model.service.IResponsablesService;

@Service
public class ResponsablesServiceImpl implements IResponsablesService  {

	private static final Logger log = LoggerFactory.getLogger(ResponsablesServiceImpl.class);
	
	@Autowired
	private IResponsableDDJJRepository repoREsDDJJ;
	
	@Autowired
	private IResponsableRRHHRepository repoREsRRHH;
	
	@Override
	public Map<String, Object> srvAgregarResponsableDDJJ(ResponsableDDJJModel responsableDDJJ) {
		Map<String, Object> map = new HashMap<>();
		try {
			ResponsableDDJJModel datos= new ResponsableDDJJModel();
				datos=repoREsDDJJ.save(responsableDDJJ);
			
			map.put("data", datos);
			map.put("message", "La empresa  fue registrado con éxito");
			
		} catch (Exception e) {
			log.error("ERROR GUARDAR USUARIO => " + e.toString());
			throw e;
		}
		return map;
	}

	@Override
	public Map<String, Object> srvAgregarResponsableRRHH(ResponsableRRHHModel domicilioRRHH) {
		Map<String, Object> map = new HashMap<>();
		try {
			ResponsableRRHHModel datos= new ResponsableRRHHModel();
				datos=repoREsRRHH.save(domicilioRRHH);
			
			map.put("data", datos);
			map.put("message", "La empresa  fue registrado con éxito");
			
		} catch (Exception e) {
			log.error("ERROR GUARDAR USUARIO => " + e.toString());
			throw e;
		}
		return map;
	}

}
