package com.fatfa.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatfa.model.entity.DomicilioModel;
import com.fatfa.model.entity.EmpresasModel;
import com.fatfa.model.entity.MultiBody;
import com.fatfa.model.entity.ResponsableDDJJModel;
import com.fatfa.model.entity.ResponsableRRHHModel;
import com.fatfa.model.service.IEmpresaService;
@RestController
@RequestMapping("/api/v1/empresa")
public class EmpresaController {

	@Autowired
	private IEmpresaService srvEmpresa;

	@GetMapping("/listaEmpresa")
	public ResponseEntity<?> onListaEmpresaAll() {
		return ResponseEntity.ok(srvEmpresa.srvListaEmpresas());
	}
	
	
	@PostMapping(path = "/crearEmpresa",  consumes = "application/json")
	public ResponseEntity<?> onAgregarEmpresa(@RequestBody MultiBody datos) {
		
		EmpresasModel empresa=datos.getDatoEmpresa();
		List<DomicilioModel> domicilio=datos.getDatosDomicilio();
		ResponsableDDJJModel datosDDJJ= datos.getResponsableDDJJ();
		ResponsableRRHHModel datosRRHH= datos.getResponsableRRHH();
		System.out.println(datos.getDatoEmpresa());
		
		System.out.println(datosDDJJ);
		 return ResponseEntity.ok(srvEmpresa.srvAgregarEmpresa(empresa,domicilio, datosDDJJ, datosRRHH));
	}

	@GetMapping("/buscarEmpresa")
	public ResponseEntity<?> onBuscarEmpresa(@RequestParam(name = "nombre", required = true) String nombre,
			@RequestParam(name = "razon", required = true) String razon) {
		return ResponseEntity.ok(srvEmpresa.srvBuscarEmpresaNombre(nombre, razon));
	}

	@GetMapping("/buscarEmpresaId/{idempresa}")
	public ResponseEntity<?> onBuscarEmpresaID(@PathVariable int idempresa) {
		return ResponseEntity.ok(srvEmpresa.srvBuscarEmpresaID(idempresa));
	}

	@DeleteMapping("/eliminarEmpresa/{idempresa}")
	public ResponseEntity<?> onEliminarUsuario(@PathVariable int idempresa) {
		return ResponseEntity.ok(srvEmpresa.srvEliminarEmpresas(idempresa));
	}

}
