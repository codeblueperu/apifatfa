package com.fatfa.model.serviceImp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatfa.exceptions.ErrorNotFoundException;
import com.fatfa.model.entity.ZonasModel;
import com.fatfa.model.repository.IZonasRepository;
import com.fatfa.model.service.IZonaService;

@Service
public class ZonasServiceImpl implements IZonaService {
	private static final Logger log = LoggerFactory.getLogger(ZonasServiceImpl.class);
	
	@Autowired
	private IZonasRepository repoZona;

	@Override
	public Map<String, Object> srvCrearEditarZonas(ZonasModel zonas) {
		Map<String, Object> map = new HashMap<>();
		try {
			repoZona.save(zonas);		
			map.put("message", "Zona registrada con éxito");
			
		} catch (Exception e) {
			log.error("ERROR GUARDAR ZONAS => " + e.toString());
			throw e;
		}
		return map;
	}

	@Override
	public List<ZonasModel> srvListaZonas() {
		List<ZonasModel> listZona= repoZona.findAll();
		return listZona;
	}

	@Override
	public ZonasModel srvBuscarZonasID(int idzonas) {
		ZonasModel zonas = repoZona.findById(idzonas).orElseThrow(()-> new ErrorNotFoundException("No se encontro ningun registro con el ID enviado."));
		return zonas;
	}

	@Override
	public Map<String, Object> srvEliminarZonasID(int idzonas) {
		Map<String, Object> map = new HashMap<>();
		repoZona.deleteById(idzonas);
		map.put("message", "Zona eliminado con éxito");
		return map;
	}

	@Override
	public Map<String, Object> srvEstadoZonas(int idzonas) {
		Map<String, Object> map = new HashMap<>();
		ZonasModel zonas = 	repoZona.findById(idzonas).orElseThrow(()-> new ErrorNotFoundException("No se encontro ningun registro con el ID enviado."));
		zonas.setEstado(!zonas.isEstado());
		repoZona.save(zonas);
		map.put("message", "Estado cambiado con éxito");
		return map;
	}

}
