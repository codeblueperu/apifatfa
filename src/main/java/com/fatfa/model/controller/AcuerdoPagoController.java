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

import com.fatfa.model.entity.AcuerdosPagoModel;
import com.fatfa.model.service.IAcuerdoPagoService;

@RestController
@RequestMapping("/api/v1/acuerdos")
public class AcuerdoPagoController {

	@Autowired
	private IAcuerdoPagoService srvAcuerdos;

	@GetMapping("/pagoEmpresa")
	public ResponseEntity<?> onListaAcuerdosPago(
			@RequestParam(name = "empresa", required = false, defaultValue = "0") int idEmpresa) {
		return ResponseEntity.ok(srvAcuerdos.srvListaAcuerdosPago(idEmpresa));
	}

	@GetMapping("/calcularDetalleCuota")
	public ResponseEntity<?> onCalcularDetalleCuotaAcuerdopago(@RequestParam("montoCapital") float montoCapital,
			@RequestParam("tasaInteres") int tasaInteres, @RequestParam("numeroCuotas") int numCuota) {
		return ResponseEntity.ok(srvAcuerdos.srvCalcularDetalleCuotas(montoCapital, tasaInteres, numCuota));
	}

	@PostMapping("/nuevoacuerdopago")
	public ResponseEntity<?> onNuevoAcuerdopago(@Valid @RequestBody AcuerdosPagoModel acuerdopago) {
		return ResponseEntity.ok(srvAcuerdos.srvNuevoAcuerdoPago(acuerdopago));
	}
}
