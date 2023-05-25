package com.fatfa.model.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatfa.model.entity.DeclaradosModel;
import com.fatfa.model.entity.EmpresasModel;
import com.fatfa.model.service.IDeclaradosService;

@RequestMapping("/api/v1/declarados")
@RestController
public class DeclaradosController {

	@Autowired
	private IDeclaradosService srvDelcrados;
	
	@PostMapping("/saveDeclarados")
	public ResponseEntity<?> onAgregarDeclarados(@RequestBody DeclaradosModel datos) {
		return ResponseEntity.ok(srvDelcrados.srvAgregarDeclarados(datos));
	}
	
	@GetMapping("/copiaDeclarados")
	public ResponseEntity<?> onCopiarDeclarados(
			@RequestParam("empresa") EmpresasModel empresa,
			@RequestParam("mes") String mes,
			@RequestParam("anio") String anio,
			@RequestParam("rectificativa") Integer rectificativa,
			@RequestParam("newmes") String newmes,
			@RequestParam("newanio") String newanio,
			@RequestParam("newrectificativa") Integer newrectificativa) {
		System.out.println(empresa);
		Map<String, Object> map = new HashMap<>();
		List<DeclaradosModel> datos = new ArrayList<>();
		datos=srvDelcrados.srvBuscarDeclarados(empresa,mes,anio,rectificativa);
		if(datos.isEmpty()) {
			map.put("data",datos);
			map.put("message", "Datos no encontrados");
		}else {
			DeclaradosModel idDelcrados= new DeclaradosModel();
			List<DeclaradosModel> newdatos=new ArrayList<>();
			for (int i = 0; i < datos.size(); i++) {
				datos.get(i).setIdDeclarado(idDelcrados.getIdDeclarado());
				datos.get(i).setAnio(newanio);
				datos.get(i).setMes(newmes);
				datos.get(i).setRectificativa(newrectificativa);
				newdatos.add(datos.get(i));
			}
			newdatos=srvDelcrados.srvCopiarDeclarados(newdatos);
			map.put("data",newdatos);
			map.put("message", "Nómina Copiada con éxito");
		}
		return ResponseEntity.ok(map);
	}
	

	@GetMapping("/buscarDeclarados")
	public ResponseEntity<?> onBuscarDeclrados(@RequestParam("empresa") EmpresasModel empresa,@RequestParam("mes") String mes,@RequestParam("anio") String anio,@RequestParam("rectificativa") Integer rectificativa) {
		
		return ResponseEntity.ok(srvDelcrados.srvBuscarDeclarados(empresa,mes,anio,rectificativa));
	}
}
