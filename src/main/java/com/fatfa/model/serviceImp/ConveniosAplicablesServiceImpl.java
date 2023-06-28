package com.fatfa.model.serviceImp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatfa.exceptions.ErrorNotFoundException;
import com.fatfa.model.entity.ConveniosAplicablesModel;
import com.fatfa.model.repository.IConveniosAplicablesRepository;
import com.fatfa.model.service.ConveniosAplicablesService;

@Service
public class ConveniosAplicablesServiceImpl implements ConveniosAplicablesService {

	private static final Logger log = LoggerFactory.getLogger(ConveniosAplicablesServiceImpl.class);
	
	@Autowired
	private IConveniosAplicablesRepository repoConvenios;

	@Override
	public Map<String, Object> srvCrearEditarConvenios(ConveniosAplicablesModel datos) {
		Map<String, Object> map = new HashMap<>();
		try {
			repoConvenios.save(datos);		
			map.put("message", "Convenio registrado con éxito");
			
		} catch (Exception e) {
			log.error("ERROR GUARDAR CONVENIOS => " + e.toString());
			throw e;
		}
		return map;
	}

	@Override
	public List<ConveniosAplicablesModel> srvListaConvenios() {
		List<ConveniosAplicablesModel> listConvenio= repoConvenios.findAll();
		return listConvenio;
	}

	@Override
	public ConveniosAplicablesModel srvBuscarConveniosID(int idconvenio) {
		ConveniosAplicablesModel convenios = repoConvenios.findById(idconvenio).orElseThrow(()-> new ErrorNotFoundException("No se encontro ningun registro con el ID enviado."));
		return convenios;
	}

	@Override
	public Map<String, Object> srvEliminarConveniosID(int idconvenio) {
		Map<String, Object> map = new HashMap<>();
		repoConvenios.deleteById(idconvenio);
		map.put("message", "Convenio eliminado con éxito");
		return map;
	}

	@Override
	public Map<String, Object> srvEstadoConvenios(int idconvenio) {
		Map<String, Object> map = new HashMap<>();
		ConveniosAplicablesModel convenios = 	repoConvenios.findById(idconvenio).orElseThrow(()-> new ErrorNotFoundException("No se encontro ningun registro con el ID enviado."));
		convenios.setActivo(!convenios.isActivo());
		repoConvenios.save(convenios);
		map.put("message", "Estado cambiado con éxito");
		return map;
	}
}
