package com.fatfa.model.serviceImp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatfa.exceptions.ErrorNotFoundException;
import com.fatfa.model.entity.SindicatosModel;
import com.fatfa.model.repository.ISindicatosRepository;
import com.fatfa.model.service.ISindicatoService;

@Service
public class SindicatoServiceImp implements ISindicatoService {
	private static final Logger log = LoggerFactory.getLogger(SindicatoServiceImp.class);
	
	@Autowired
	private ISindicatosRepository repoSindicato;

	@Override
	public Map<String, Object> srvCrearEditarSindicato(SindicatosModel sindicato) {
		Map<String, Object> map = new HashMap<>();
		try {
			repoSindicato.save(sindicato);		
			map.put("message", "Sindicatoe registrado con éxito");
			
		} catch (Exception e) {
			log.error("ERROR GUARDAR SINDICATO => " + e.toString());
			throw e;
		}
		return map;
	}

	@Override
	public List<SindicatosModel> srvListaSindicatos() {
		
		List<SindicatosModel> listaSindicatos = repoSindicato.findAll();
		
		return listaSindicatos;
	}

	@Override
	public SindicatosModel srvBuscarSindicatoID(int idSindicato) {
		SindicatosModel sindicato = 	repoSindicato.findById(idSindicato).orElseThrow(()-> new ErrorNotFoundException("No se encontro ningun registro con el ID enviado."));
		return sindicato;
	}

	@Override
	public Map<String, Object> srvEliminarSindicatoID(int idSindicato) {
		Map<String, Object> map = new HashMap<>();
		repoSindicato.deleteById(idSindicato);
		map.put("message", "Sindicato eliminado con éxito");
		return map;
	}

}
