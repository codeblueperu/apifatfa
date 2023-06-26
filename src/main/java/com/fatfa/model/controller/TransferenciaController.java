package com.fatfa.model.controller;

import java.text.ParseException;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatfa.model.entity.TransferenciaModel;
import com.fatfa.model.service.ITransferenciaService;
import com.fatfa.utils.Constantes;

@RestController
@RequestMapping("api/v1/transferencia")
public class TransferenciaController {

	@Autowired
	private ITransferenciaService srvTransferencias;
	
	@GetMapping("/listatransferencia")
	public ResponseEntity<?> onListaTranferencia(@RequestParam("idEmpresa") int idEmpresa,
			@RequestParam("estado") String estado, @RequestParam("fecha")String fecha) throws ParseException {
		System.err.println(fecha);
		return ResponseEntity.ok(srvTransferencias.srvListarTransaferencia(idEmpresa, estado, Constantes.utilConvertirFecha(fecha, "yyyy-MM-dd")));
	}
	
	
	@PostMapping("/savetransferencia")
	public ResponseEntity<?> onAgregarTransferencia(@Valid @RequestBody TransferenciaModel datos){		
		return ResponseEntity.ok(srvTransferencias.srvAgregarTransferencia(datos));
	}
	
}
