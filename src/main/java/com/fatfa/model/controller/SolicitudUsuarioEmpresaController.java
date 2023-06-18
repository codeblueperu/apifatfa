package com.fatfa.model.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatfa.model.entity.SolicitudesUsuarioEmpresaModel;
import com.fatfa.model.service.ISolicitudUsuarioEmpresaService;

@RestController
@RequestMapping("api/v1/solicitud")
public class SolicitudUsuarioEmpresaController {

	@Autowired
	private ISolicitudUsuarioEmpresaService srvSolicitud;

	@GetMapping("/listaSolicitudesUsuarioEmpresa")
	public ResponseEntity<?> onListaSolicitudesUsuarioEmpresa(@RequestParam("idEmpresa") int idEmpresa,
			@RequestParam("estadoSolicitud") String estadoSolicitud) {
		return ResponseEntity.ok(srvSolicitud.srvListaSolicitudesUsuarioEmpresa(idEmpresa, estadoSolicitud));
	}
	
	@PostMapping("/nuevaSolicitudUsuarioEmpresa")
	public ResponseEntity<?> onNuevaSolicitudesUsuarioEmpresa(@Valid @RequestBody SolicitudesUsuarioEmpresaModel solicitud) {
		return ResponseEntity.ok(srvSolicitud.srvCreateUpdateSolicitudUsuarioEmpresa(solicitud));
	}
	
	@GetMapping("/buscarSolicitudesUsuarioEmpresa")
	public ResponseEntity<?> onBuscarSolicitudesUsuarioEmpresa(@RequestParam("idSolicitud") int idSolicitud) {
		return ResponseEntity.ok(srvSolicitud.srvBuscarSolicitudUsuarioEmpresaID(idSolicitud));
	}
	
	@PostMapping("/anularSolicitudUsuarioEmpresa")
	public ResponseEntity<?> onAnularSolicitudesUsuarioEmpresa(@RequestParam("idSolicitud")int idSolicitud, @RequestParam("observaciones")String observaciones) {
		return ResponseEntity.ok(srvSolicitud.srvAnularSolicitudUsuarioEmpresa(idSolicitud, observaciones));
	}
	
	@PostMapping("/aprobarSolicitudUsuarioEmpresa")
	public ResponseEntity<?> onAprobarSolicitudesUsuarioEmpresa(@RequestParam("idSolicitud")int idSolicitud) {
		return ResponseEntity.ok(srvSolicitud.srvAprobarSolicitudUsuarioEmpresa(idSolicitud));
	}

}
