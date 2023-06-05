package com.fatfa.model.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatfa.model.entity.BoletaModel;
import com.fatfa.model.service.IBoletaService;

@RestController
@RequestMapping("/api/v1/boleta")
public class BoletasController {

	@Autowired
	private IBoletaService srvBoleta;

	@GetMapping("/datosIniciales")
	public ResponseEntity<?> srvGenerarDatosIncialesBoleta(@RequestParam("idEmpresa") int idEmpresa,
			@RequestParam("anio") String anio, @RequestParam("mes") String mes,
			@RequestParam("totalDias") int totalDias) {

		return ResponseEntity.ok(srvBoleta.onCalcularMontoBoleta(idEmpresa, anio, mes, totalDias));
	}
	
	@PostMapping("/guardarDataBoleta")
	public void srvGuardarDatosBoleta(@RequestBody BoletaModel dataBoleta, HttpServletRequest request, HttpServletResponse response) {
		srvBoleta.onGenerarTalonBoletaPago(dataBoleta,  request,  response);			
		//return ResponseEntity.ok().body("EXCELENTE");
	}
}
