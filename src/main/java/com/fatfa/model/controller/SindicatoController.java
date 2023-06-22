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

import com.fatfa.model.entity.SindicatosModel;
import com.fatfa.model.service.ISindicatoService;

@RestController
@RequestMapping("/api/v1/sindicato")
public class SindicatoController {

	@Autowired
	private ISindicatoService srvSindicato;
	
	@GetMapping("/listaData")
	public ResponseEntity<?> onListarSindicatos() {
		return ResponseEntity.ok(srvSindicato.srvListaSindicatos());
	}
	
	@PostMapping("/nuevoEditar")
	public ResponseEntity<?> onNuevoSindicatoEditar(@RequestBody SindicatosModel datos) {
		return ResponseEntity.ok(srvSindicato.srvCrearEditarSindicato(datos));
	}
	
	@GetMapping("/buscarId")
	public ResponseEntity<?> onBuscarSindicatoID(@RequestParam("idsindicato") int idSindicato) {
		return ResponseEntity.ok(srvSindicato.srvBuscarSindicatoID(idSindicato));
	}
	
	
	@PostMapping("/estadoId")
	public ResponseEntity<?> onEstadoSindicato(@RequestParam("idsindicato") int idSindicato) {
		return ResponseEntity.ok(srvSindicato.srvEstadoSindicato(idSindicato));
	}
	
	@DeleteMapping("/eliminarId")
	public ResponseEntity<?> onEliminarSindicatoID(@RequestParam("idsindicato") int idSindicato) {
		return ResponseEntity.ok(srvSindicato.srvEliminarSindicatoID(idSindicato));
	}
}
