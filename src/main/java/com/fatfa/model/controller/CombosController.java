package com.fatfa.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatfa.model.entity.PartidosModel;
import com.fatfa.model.entity.ProvinciasModel;
import com.fatfa.model.service.CombosService;

@RestController
@RequestMapping("/api/v1/combo")
public class CombosController {
	
	@Autowired
	private CombosService srvCombo;
	
	@GetMapping("/listaJuridiccion")
	public ResponseEntity<?> onListaJuridiccionAll() {
		return ResponseEntity.ok(srvCombo.srvJuridiccion());
	}
	
	@GetMapping("/listaLocalidad")
	public ResponseEntity<?> onLocalidadAll(@RequestParam("IdProvincia") ProvinciasModel IdProvincia, @RequestParam("IdPartidos") PartidosModel IdPartidos) {
		return ResponseEntity.ok(srvCombo.srvLocalidad(IdProvincia, IdPartidos));
	}
	
	@GetMapping("/listaPartidos")
	public ResponseEntity<?> onPartidosAll(@RequestParam("IdProvincia") ProvinciasModel IdProvincia) {
		System.out.println(IdProvincia);
		return ResponseEntity.ok(srvCombo.srvPartidos(IdProvincia));
	}
	
	@GetMapping("/listaProvincia")
	public ResponseEntity<?> onListaProvinciaAll() {
		return ResponseEntity.ok(srvCombo.srvProvincias());
	}
	
	@GetMapping("/listaActividades")
	public ResponseEntity<?> onListaActividadesAll() {
		return ResponseEntity.ok(srvCombo.srvActividadEconomica());
	}
	
	@GetMapping("/listaConvenios")
	public ResponseEntity<?> onListaConveniosAll() {
		return ResponseEntity.ok(srvCombo.srvConvenioAplicable());
	}
	
	@GetMapping("/listaZonas")
	public ResponseEntity<?> onListaZonasll() {
		return ResponseEntity.ok(srvCombo.srvZonas());
	}
	
	@GetMapping("/listaCategoria")
	public ResponseEntity<?> onListaCategoriaAll() {
		return ResponseEntity.ok(srvCombo.srvCategorias());
	}
	
	@GetMapping("/listaEstado")
	public ResponseEntity<?> onListaEstadoAll() {
		return ResponseEntity.ok(srvCombo.srvEstado());
	}
	
	@GetMapping("/ListaSindicato")
	public ResponseEntity<?> onListaSIndicatoAll() {
		return ResponseEntity.ok(srvCombo.srvSindicato());
	}

}
