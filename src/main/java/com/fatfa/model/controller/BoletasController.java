package com.fatfa.model.controller;

import java.text.ParseException;

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
import org.springframework.web.multipart.MultipartFile;

import com.fatfa.model.entity.BoletaModel;
import com.fatfa.model.service.IBoletaImportacionService;
import com.fatfa.model.service.IBoletaService;
import com.fatfa.utils.Constantes;

@RestController
@RequestMapping("/api/v1/boleta")
public class BoletasController {

	@Autowired
	private IBoletaService srvBoleta;
	
	@Autowired
	private IBoletaImportacionService srvBoletaImportacion;

	@GetMapping("/datosIniciales")
	public ResponseEntity<?> srvGenerarDatosIncialesBoleta(@RequestParam("idEmpresa") int idEmpresa,
			@RequestParam("anio") String anioPeriodoPago, @RequestParam("mes") String mesPeriodoPago,
			@RequestParam("fechaPosiblePago") String fechaPosiblePago) throws ParseException {
		return ResponseEntity.ok(srvBoleta.onCalcularMontoBoleta(idEmpresa, anioPeriodoPago, mesPeriodoPago, Constantes.utilConvertirFecha(fechaPosiblePago, "yyyy-MM-dd")));
	}
	
	@PostMapping("/guardarDataBoleta")
	public void srvGuardarDatosBoleta(@RequestBody BoletaModel dataBoleta, HttpServletRequest request, HttpServletResponse response) {
		srvBoleta.onGenerarTalonBoletaPago(dataBoleta,  request,  response);	
	}
	
	@GetMapping("/verificador")
	public ResponseEntity<?> srverificador(@RequestParam("digitos") String codigobarra) throws ParseException {
		return ResponseEntity.ok().body(Constantes.generarDigitoVerificador(codigobarra));
	}
	
	@GetMapping("/listBoleta")
	public ResponseEntity<?> srvListBoletas(@RequestParam("idEmpresa") int idEmpresa, @RequestParam("idAporte") int aporte, @RequestParam("mes") String mes, @RequestParam("anio") String anio){
		return ResponseEntity.ok(srvBoleta.srvLisBoleta(idEmpresa, aporte, mes, anio));
	}
	
	@PostMapping("/imprtarMediopago")
	public  ResponseEntity<?> srvImportarBoletaPago(@RequestParam (name = "file", required = false) MultipartFile file) {
		return ResponseEntity.ok(srvBoletaImportacion.srvImportarBoleta(file));
	}
}
