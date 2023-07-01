package com.fatfa.model.serviceImp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatfa.exceptions.ErrorNotFoundException;
import com.fatfa.model.entity.CategoriasModelo;
import com.fatfa.model.repository.ICategoriasRepository;
import com.fatfa.model.service.ICategoriaService;

@Service
public class CategoriaServiceImp implements ICategoriaService {
	private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImp.class);
	
	@Autowired
	private ICategoriasRepository repoCategoria;

	@Override
	public Map<String, Object> srvCrearEditarCategoria(CategoriasModelo categoria) {
		Map<String, Object> map = new HashMap<>();
		try {
			repoCategoria.save(categoria);		
			map.put("message", "Categoria registrado con éxito");
			
		} catch (Exception e) {
			log.error("ERROR GUARDAR CATEGORIA => " + e.toString());
			throw e;
		}
		return map;
	}

	@Override
	public List<CategoriasModelo> srvListaCategoria() {
		List<CategoriasModelo> listCategoria= repoCategoria.findAll();
		return listCategoria;
	}

	@Override
	public CategoriasModelo srvBuscarCategoriaID(String idCategoria) {
		CategoriasModelo categoria = repoCategoria.findById(idCategoria).orElseThrow(()-> new ErrorNotFoundException("No se encontro ningun registro con el ID enviado."));
		return categoria;
	}

	@Override
	public Map<String, Object> srvEliminarCategoriaID(String idCategoria) {
		Map<String, Object> map = new HashMap<>();
		repoCategoria.deleteById(idCategoria);
		map.put("message", "Categoria eliminado con éxito");
		return map;
	}

	@Override
	public Map<String, Object> srvEstadoCategoria(String idCategoria) {
		Map<String, Object> map = new HashMap<>();
		CategoriasModelo sindicato = 	repoCategoria.findById(idCategoria).orElseThrow(()-> new ErrorNotFoundException("No se encontro ningun registro con el ID enviado."));
		sindicato.setEstado(!sindicato.isEstado());
		repoCategoria.save(sindicato);
		map.put("message", "Estado cambiado con éxito");
		return map;
	}
}
