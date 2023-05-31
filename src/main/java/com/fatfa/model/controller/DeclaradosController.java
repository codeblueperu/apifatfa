package com.fatfa.model.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fatfa.model.entity.NominasModel;
import com.fatfa.model.entity.EmpresasModel;
import com.fatfa.model.service.IDeclaradosService;

@RequestMapping("/api/v1/declarados")
@RestController
public class DeclaradosController {

	@Autowired
	private IDeclaradosService srvDelcrados;
	
	@PostMapping(path="/saveDeclarados")
	public ResponseEntity<?> onAgregarDeclarados(@RequestPart(name="datos") NominasModel datos, @RequestParam(name ="file",required = false ) MultipartFile file) {
		
		System.out.println(file);
		if (file==null) {
			return ResponseEntity.ok(srvDelcrados.srvAgregarDeclarados(datos));
		}else {
			String nombre= file.getOriginalFilename();
			datos.setNombreArchivo(nombre);
			
			srvDelcrados.saveFile(file);
			return ResponseEntity.ok(srvDelcrados.srvAgregarDeclarados(datos));
		}
		
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
		
		Map<String, Object> map = new HashMap<>();
		List<NominasModel> datos = new ArrayList<>();
		datos=srvDelcrados.srvBuscarDeclarados(empresa,mes,anio,rectificativa);
		if(datos.isEmpty()) {
			map.put("data",datos);
			map.put("message", "Datos no encontrados");
		}else {
			List<NominasModel> newdatos=new ArrayList<>();
			for (int i = 0; i < datos.size(); i++) {
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
	
	@GetMapping("/buscarId")
	public ResponseEntity<?> onBuscarDeclradosID(@RequestParam("id") Integer id) {
		return ResponseEntity.ok(srvDelcrados.srvBuscarDeclaradosID(id));
	}
	
	/**
	 * @author SOPORTE
	 * @apiNote CONTROLLADOR
	 * @param file
	 * @param anio
	 * @param mes
	 * @return
	 */
	@PostMapping("/cargaMasivaNominas")
	public ResponseEntity<?> onCargarnominasMasivo(@RequestParam("file") MultipartFile file,
			@RequestParam(name = "anio", required = true) String anio,
			@RequestParam(name = "mes", required = true) String mes) {
		return ResponseEntity.ok(srvDelcrados.srvGuardarNominaMasiva(file, 0, anio, mes, 0));
	}
}
