package com.fatfa.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatfa.model.entity.DomicilioModel;
import com.fatfa.model.service.IDomicilioService;

@RestController
@RequestMapping("/api/v1/domicilio")
public class DomicilioController {
	
	@Autowired
	private IDomicilioService srvDomicilio;
	@PostMapping("/saveDomicilo")
	public ResponseEntity<?> onAgregarDomicilio(@RequestBody List<DomicilioModel> datos) {
		return ResponseEntity.ok(srvDomicilio.srvAgregarDomicilio(datos));
	}

}
