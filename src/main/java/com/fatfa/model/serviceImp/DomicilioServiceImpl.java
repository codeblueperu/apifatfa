package com.fatfa.model.serviceImp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatfa.model.entity.DomicilioModel;
import com.fatfa.model.repository.IDomicilioRepository;
import com.fatfa.model.service.IDomicilioService;

@Service
public class DomicilioServiceImpl implements IDomicilioService {
	
private static final Logger log = LoggerFactory.getLogger(DomicilioServiceImpl.class);
	
	@Autowired
	private IDomicilioRepository repoDomicilio;


	@Override
	public Map<String, Object> srvAgregarDomicilio(List<DomicilioModel> domicilio) {
		Map<String, Object> map = new HashMap<>();
		try {
			List<DomicilioModel> datos = new ArrayList<>();
			
			datos=repoDomicilio.saveAll(domicilio);
			
			map.put("data", datos.toString());
			map.put("message", "El Domicilio  fue registrado con éxito");
			
		} catch (Exception e) {
			log.error("ERROR GUARDAR DOMICILIO => " + e.toString());
			throw e;
		}
		return map;
	}

}
