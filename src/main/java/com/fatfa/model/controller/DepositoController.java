package com.fatfa.model.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatfa.model.entity.DepositosModel;
import com.fatfa.model.service.DepositoService;

@RestController
@RequestMapping("/api/v1/deposito")
public class DepositoController {

	@Autowired
	private DepositoService srvDeposito;
	
	@GetMapping("/listDepositos")
	public ResponseEntity<?> onBuscarDeposito(@RequestParam("empresa") int empresa,
			@RequestParam("estado") int estado, @RequestParam("fecha") Date fecha,
			@RequestParam("fechaF") Date fechaF) {
		return ResponseEntity.ok(srvDeposito.srvfindByEmpresaIdEmpresaAndEstadoPagoIdEstadoPagoAndFechaPagoBetween(empresa, estado, fecha, fechaF));
	}
	
	@GetMapping("/listDepositosID")
	public ResponseEntity<?> onBuscarDepositoID(@RequestParam("idDeposito") int idDeposito) {
		return ResponseEntity.ok(srvDeposito.srvBuscarDepositoID(idDeposito));
	}
	
	@PostMapping("/saveDepositos")
	public ResponseEntity<?> onBuscarDeclradosID(@RequestBody DepositosModel datos) {
		return ResponseEntity.ok(srvDeposito.srvSaveDepositos(datos));
	}
	
	
	@PostMapping("/updateDepositos")
	public ResponseEntity<?> onUpdateDeclrados(@RequestParam("idDeposito") int deposito,
			@RequestParam("estado") int estado) {
		return ResponseEntity.ok(srvDeposito.srvUpdateDepositos(deposito, estado));
	}
	
	
}
