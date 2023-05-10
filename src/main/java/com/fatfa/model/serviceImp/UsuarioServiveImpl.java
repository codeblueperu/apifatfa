package com.fatfa.model.serviceImp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fatfa.exceptions.ErrorConflictException;
import com.fatfa.exceptions.ErrorNotFoundException;
import com.fatfa.model.entity.UsuarioModel;
import com.fatfa.model.repository.IUsersRepository;
import com.fatfa.model.service.IUsersService;

@Service
@Transactional
public class UsuarioServiveImpl implements IUsersService {
	private static final Logger log = LoggerFactory.getLogger(UsuarioServiveImpl.class);

	@Autowired
	private IUsersRepository repoUser;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public UsuarioModel srvBuscarUsuarioEmail(String email) {
		UsuarioModel userDB = new UsuarioModel();
		try {
			userDB = repoUser.findByEmail(email).orElseThrow(() -> new ErrorNotFoundException(
					"El emial <b>" + email + "</b>, no se encuentra resgistrado en nuestra base de datos."));

		} catch (Exception e) {
			log.error("ERROR BUSCAR USUARIO EMAIL => " + e.toString());
			throw e;
		}
		return userDB;
	}

	@Override
	public List<UsuarioModel> srvListaUsuario() {
		List<UsuarioModel> usersDB = new ArrayList<>();
		try {
			usersDB = repoUser.findAll();
		} catch (Exception e) {
			log.error("ERROR LISTAR USUARIOS => " + e.toString());
			throw e;
		}
		return usersDB;
	}

	@Override
	public Map<String, Object> srvCrearUsuario(UsuarioModel usuario) {
		Map<String, Object> map = new HashMap<>();
		try {
			UsuarioModel userDB = new UsuarioModel();

			Optional<UsuarioModel> emailExiste = repoUser.findByEmail(usuario.getEmail());

			if (emailExiste.isPresent()) {
				throw new ErrorConflictException("El email <b>" + usuario.getEmail()
						+ "</b>, ya se encuentra registrado en nuestra Base de Datos");
			} else {
				usuario.setPassword(encoder.encode(usuario.getPassword()));
				userDB = repoUser.save(usuario);
			}

			map.put("data", userDB);
			map.put("message", "El usuario fue registrado con éxito");

		} catch (Exception e) {
			log.error("ERROR GUARDAR USUARIO => " + e.toString());
			throw e;
		}
		return map;
	}

	@Override
	public UsuarioModel srvBuscarusuarioID(int idUsuario) {

		return repoUser.findById(idUsuario)
				.orElseThrow(() -> new ErrorNotFoundException("No se encontro ningun usuario con el ID " + idUsuario));
	}

	@Override
	public Map<String, Object> srvActualizarUsuario(UsuarioModel usuario, int idUsuario) {
		Map<String, Object> map = new HashMap<>();
		try {
			UsuarioModel userDB = new UsuarioModel();

			userDB = repoUser.findById(idUsuario).orElseThrow(() -> new ErrorConflictException(
					"No se puede realizar una actualización con un ID que no éxiste."));

//			SOLO SI LA CLAVE ES DIFERENTE SE PROCEDERA A REALIZAR LA NUEVA ENCRIPTACION A LA NUEVA CLAVE
			if (!encoder.matches(userDB.getPassword(), usuario.getPassword())) {
				usuario.setPassword(encoder.encode(usuario.getPassword()));
			}

			userDB = repoUser.save(usuario);

			map.put("data", userDB);
			map.put("message", "El usuario fue actualizado con éxito");

		} catch (Exception e) {
			log.error("ERROR ACTUALIZAR USUARIO => " + e.toString());
			throw e;
		}
		return map;
	}

	@Override
	public Map<String, Object> srvEiminarUsuario(int idUsuario) {
		Map<String, Object> map = new HashMap<>();
		try {
			UsuarioModel userDB = new UsuarioModel();

			userDB = repoUser.findById(idUsuario).orElseThrow(
					() -> new ErrorConflictException("No se puede realizar una eliminacion con un ID que no éxiste."));

			map.put("message", "El usuario <b>" + userDB.getEmail() + "</b> fue eliminado con éxito");

		} catch (Exception e) {
			log.error("ERROR ELIMINAR USUARIO => " + e.toString());
			throw e;
		}
		return map;
	}

}
