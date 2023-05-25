package com.fatfa.model.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatfa.model.entity.ResponsableDDJJModel;
import com.fatfa.model.entity.ResponsableRRHHModel;
import com.fatfa.model.service.IResponsablesService;

@RestController
@RequestMapping("/api/v1/responsable")
public class ResponsablesController {

	@Autowired
	private IResponsablesService srvResponsable;
	
	@PostMapping("/saveRRHH")
	public ResponseEntity<?> onAgregarResponsableRRHH(@RequestBody ResponsableRRHHModel datos) {
		return ResponseEntity.ok(srvResponsable.srvAgregarResponsableRRHH(datos));
	}
	
	@PostMapping("/saveDDJJ")
	public ResponseEntity<?> onAgregarResponsableDDJJ(@RequestBody ResponsableDDJJModel datos) {
		return ResponseEntity.ok(srvResponsable.srvAgregarResponsableDDJJ(datos));
	}
}
