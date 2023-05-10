package com.fatfa.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatfa.model.entity.UsuarioModel;
import com.fatfa.model.service.IUsersService;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

	@Autowired
	private IUsersService srvUsuario;

	@GetMapping("/listaUsuarios")
	public ResponseEntity<?> onListaUsuariosAll() {
		return ResponseEntity.ok(srvUsuario.srvListaUsuario());
	}

	@GetMapping("/buscarEmailUsuario")
	public ResponseEntity<?> onBuscarEmailusuario(@RequestParam(name = "email", required = true) String email) {
		return ResponseEntity.ok(srvUsuario.srvBuscarUsuarioEmail(email));
	}

	@PostMapping("/crearUsuario")
	public ResponseEntity<?> onCrearNuevoUsuario(@RequestBody UsuarioModel usuario) {
		return ResponseEntity.ok(srvUsuario.srvCrearUsuario(usuario));
	}

	@GetMapping("/buscarUsuarioId/{idusuario}")
	public ResponseEntity<?> onBuscarUsuarioID(@PathVariable int idusuario) {
		return ResponseEntity.ok(srvUsuario.srvBuscarusuarioID(idusuario));
	}

	@PutMapping("/updateUsuario/{idusuario}")
	public ResponseEntity<?> onUpdateUsuario(@RequestBody UsuarioModel usuario, @PathVariable int idusuario) {
		return ResponseEntity.ok(srvUsuario.srvActualizarUsuario(usuario, idusuario));
	}

	@DeleteMapping("/eliminarUsuario/{idusuario}")
	public ResponseEntity<?> onEliminarUsuario(@PathVariable int idusuario) {
		return ResponseEntity.ok(srvUsuario.srvEiminarUsuario(idusuario));
	}

}
