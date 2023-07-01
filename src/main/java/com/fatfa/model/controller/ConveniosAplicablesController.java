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

import com.fatfa.model.entity.ConveniosAplicablesModel;
import com.fatfa.model.service.ConveniosAplicablesService;

@RestController
@RequestMapping("/api/v1/convenio")
public class ConveniosAplicablesController {

	@Autowired
	private ConveniosAplicablesService srvConvenio;
	@GetMapping("/listaData")
	public ResponseEntity<?> onListarSindicatos() {
		return ResponseEntity.ok(srvConvenio.srvListaConvenios());
	}
	
	@PostMapping("/nuevoEditar")
	public ResponseEntity<?> onNuevoConvenioEditar(@RequestBody ConveniosAplicablesModel datos) {
		return ResponseEntity.ok(srvConvenio.srvCrearEditarConvenios(datos));
	}
	
	@GetMapping("/buscarId")
	public ResponseEntity<?> onBuscarConvenioID(@RequestParam("idconvenio") int idconvenio) {
		return ResponseEntity.ok(srvConvenio.srvBuscarConveniosID(idconvenio));
	}
		
	@PostMapping("/estadoId")
	public ResponseEntity<?> onEstadoConvenio(@RequestParam("idconvenio") int idconvenio) {
		return ResponseEntity.ok(srvConvenio.srvEstadoConvenios(idconvenio));
	}
	
	@DeleteMapping("/eliminarId")
	public ResponseEntity<?> onEliminarConvenioID(@RequestParam("idconvenio") int idconvenio) {
		return ResponseEntity.ok(srvConvenio.srvEliminarConveniosID(idconvenio));
	}
}
