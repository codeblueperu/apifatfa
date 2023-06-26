package com.fatfa.model.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fatfa.model.entity.TransferenciaModel;
import com.fatfa.model.service.IDeclaradosService;
import com.fatfa.model.service.ITransferenciaService;
import com.fatfa.utils.Constantes;

@RestController
@RequestMapping("api/v1/transferencia")
public class TransferenciaController {

	@Autowired
	private ITransferenciaService srvTransferencias;
	
	@Autowired
	private IDeclaradosService srvDelcrados;
	
	@GetMapping("/listatransferencia")
	public ResponseEntity<?> onListaTranferencia(@RequestParam("idEmpresa") int idEmpresa,
			@RequestParam("estado") String estado, @RequestParam("fecha")String fecha) throws ParseException {
		System.err.println(fecha);
		return ResponseEntity.ok(srvTransferencias.srvListarTransaferencia(idEmpresa, estado, Constantes.utilConvertirFecha(fecha, "yyyy-MM-dd")));
	}
	
	
	@PostMapping("/savetransferencia")
	public ResponseEntity<?> onAgregarTransferencia(@RequestPart(name = "datos") TransferenciaModel datos, @RequestParam (name = "file", required = false) MultipartFile file){
		if(file==null) {
			return ResponseEntity.ok(srvTransferencias.srvAgregarTransferencia(datos));
		}else {
			String nombre = file.getOriginalFilename();
			datos.setComprobante(nombre);
			srvDelcrados.saveFile("transferencia//",file);
			return ResponseEntity.ok(srvTransferencias.srvAgregarTransferencia(datos));
		}
		
	}
	
}
