package com.fatfa.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatfa.model.entity.CategoriasModelo;
import com.fatfa.model.service.ICategoriaService;

@RestController
@RequestMapping("/api/v1/categoria")
public class CategoriaController {

	@Autowired
	private ICategoriaService srvCategoria;
	
	@GetMapping("/listaData")
	public ResponseEntity<?> onListarSindicatos() {
		return ResponseEntity.ok(srvCategoria.srvListaCategoria());
	}
	
	@PostMapping("/nuevoEditar")
	public ResponseEntity<?> onNuevoCategoriaEditar(@RequestBody CategoriasModelo datos) {
		return ResponseEntity.ok(srvCategoria.srvCrearEditarCategoria(datos));
	}
	
	@GetMapping("/buscarId")
	public ResponseEntity<?> onBuscarCategoriaID(@RequestParam("idCategoria") String idCategoria) {
		return ResponseEntity.ok(srvCategoria.srvBuscarCategoriaID(idCategoria));
	}
	
	
	@PostMapping("/estadoId")
	public ResponseEntity<?> onEstadoCategoria(@RequestParam("idCategoria") String idCategoria) {
		return ResponseEntity.ok(srvCategoria.srvEstadoCategoria(idCategoria));
	}
	
	@DeleteMapping("/eliminarId")
	public ResponseEntity<?> onEliminarCategoriaID(@RequestParam("idsindicato") String idCategoria) {
		return ResponseEntity.ok(srvCategoria.srvEliminarCategoriaID(idCategoria));
	}
	
}
