package com.fatfa.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatfa.model.entity.PerfilesModel;
import com.fatfa.model.service.IPerfilesService;

@RestController
@RequestMapping("/api/v1/perfiles")
public class PerfilController {

	@Autowired
	private IPerfilesService srvPerfil;

	@GetMapping("/listaPerfiles")
	public ResponseEntity<?> onListaPerfilesAll() {
		return ResponseEntity.ok(srvPerfil.srvListaPerfilesAll());
	}

	@PostMapping("/createPerfil")
	public ResponseEntity<?> onCrearNuevoPerfil(@RequestBody PerfilesModel perfilData) {
		return ResponseEntity.ok(srvPerfil.srvGuardarNuevoPerfil(perfilData));
	}

	@GetMapping("/buscarPerfilId/{idperfil}")
	public ResponseEntity<?> onBuscarPerfilID(@PathVariable int idperfil) {
		return ResponseEntity.ok(srvPerfil.srvBuscarPerfilId(idperfil));
	}

	@PutMapping("/updatePerfil/{idperfil}")
	public ResponseEntity<?> onUpdatePerfil(@RequestBody PerfilesModel perfilData, @PathVariable int idperfil) {
		return ResponseEntity.ok(srvPerfil.srvActualizarPerfil(perfilData, idperfil));
	}

	@DeleteMapping("/eliminarPerfil/{idperfil}")
	public ResponseEntity<?> onEliminarPerfil(@PathVariable int idperfil) {
		return ResponseEntity.ok(srvPerfil.srvEliminarPerfil(idperfil));
	}
}
