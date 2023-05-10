package com.fatfa.model.serviceImp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatfa.exceptions.ErrorConflictException;
import com.fatfa.exceptions.ErrorNotFoundException;
import com.fatfa.model.entity.PerfilesModel;
import com.fatfa.model.repository.IPerfilesRepository;
import com.fatfa.model.service.IPerfilesService;

@Service
@Transactional
public class PerfilesServiceImp implements IPerfilesService {
	private static final Logger log = LoggerFactory.getLogger(PerfilesServiceImp.class);

	@Autowired
	private IPerfilesRepository repoPerfil;

	@Override
	public List<PerfilesModel> srvListaPerfilesAll() {
		List<PerfilesModel> lista = new ArrayList<>();
		try {
			lista = repoPerfil.findAll();
		} catch (Exception e) {
			log.error("ERROR LISTAR PERFILES => " + e.toString());
			throw e;
		}
		return lista;
	}

	@Override
	public Map<String, Object> srvGuardarNuevoPerfil(PerfilesModel perfil) {
		Map<String, Object> map = new HashMap<>();
		try {
			PerfilesModel perfilDB = new PerfilesModel();

			Optional<PerfilesModel> existePerfil = repoPerfil.findByDescripcion(perfil.getDescripcion());

			if (existePerfil.isPresent()) {
				throw new ErrorConflictException("El perfil que intentas crear ya éxiste.");
			} else {
				perfilDB = repoPerfil.save(perfil);
			}

			map.put("data", perfilDB);
			map.put("message", "El perfil <b>" + perfilDB.getDescripcion() + "</b>, se registro con éxito.");

		} catch (Exception e) {
			log.error("ERROR GUARDAR PERFILES => " + e.toString());
			throw e;
		}
		return map;
	}

	@Override
	public PerfilesModel srvBuscarPerfilId(int idperfil) {
		
		PerfilesModel perfil = repoPerfil.findById(idperfil)
				.orElseThrow(() -> new ErrorNotFoundException("No se encontro ningun registro para el ID " + idperfil));

		return perfil;
	}

	@Override
	public Map<String, Object> srvActualizarPerfil(PerfilesModel perfil, int idPerfil) {
		Map<String, Object> map = new HashMap<>();
		try {
			PerfilesModel perfilDB = new PerfilesModel();

			repoPerfil.findById(idPerfil).orElseThrow(() -> new ErrorNotFoundException("No se puede realizar una actualizacion con un ID que no éxiste."));

			perfilDB = repoPerfil.save(perfil);			

			map.put("data", perfilDB);
			map.put("message", "El perfil <b>" + perfilDB.getDescripcion() + "</b>, se actualizo con éxito.");

		} catch (Exception e) {
			log.error("ERROR ACTUALIZAR PERFILES => " + e.toString());
			throw e;
		}
		return map;
	}

	@Override
	public Map<String, Object> srvEliminarPerfil(int idPerfil) {
		Map<String, Object> map = new HashMap<>();
		try {
			PerfilesModel perfilDB = new PerfilesModel();

			perfilDB = repoPerfil.findById(idPerfil).orElseThrow(() -> new ErrorNotFoundException("No se puede realizar una eliminación con un ID que no éxiste."));

			repoPerfil.delete(perfilDB);			


			map.put("message", "El perfil <b>" + perfilDB.getDescripcion() + "</b>, se elimino con éxito.");

		} catch (Exception e) {
			log.error("ERROR ELIMINAR PERFILES => " + e.toString());
			throw e;
		}
		return map;
	}

}
