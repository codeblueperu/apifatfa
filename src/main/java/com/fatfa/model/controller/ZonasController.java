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

import com.fatfa.model.entity.ZonasModel;
import com.fatfa.model.service.IZonaService;

@RestController
@RequestMapping("/api/v1/zona")
public class ZonasController {

	@Autowired
	private IZonaService srvZona;
	@GetMapping("/listaData")
	public ResponseEntity<?> onListarSindicatos() {
		return ResponseEntity.ok(srvZona.srvListaZonas());
	}
	
	@PostMapping("/nuevoEditar")
	public ResponseEntity<?> onNuevoZonaEditar(@RequestBody ZonasModel datos) {
		return ResponseEntity.ok(srvZona.srvCrearEditarZonas(datos));
	}
	
	@GetMapping("/buscarId")
	public ResponseEntity<?> onBuscarZonaID(@RequestParam("idzona") int idzona) {
		return ResponseEntity.ok(srvZona.srvBuscarZonasID(idzona));
	}
		
	@PostMapping("/estadoId")
	public ResponseEntity<?> onEstadoZona(@RequestParam("idzona") int idzona) {
		return ResponseEntity.ok(srvZona.srvEstadoZonas(idzona));
	}
	
	@DeleteMapping("/eliminarId")
	public ResponseEntity<?> onEliminarZonaID(@RequestParam("idzona") int idzona) {
		return ResponseEntity.ok(srvZona.srvEliminarZonasID(idzona));
	}
}
