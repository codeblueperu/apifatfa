package com.fatfa.model.serviceImp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fatfa.exceptions.ErrorConflictException;
import com.fatfa.exceptions.ErrorNotFoundException;
import com.fatfa.model.entity.SolicitudesUsuarioEmpresaModel;
import com.fatfa.model.entity.UsuarioModel;
import com.fatfa.model.entity.UsuariosEmpresaModel;
import com.fatfa.model.repository.ISolicitudUsuarioEmpresaRepository;
import com.fatfa.model.repository.IUsersRepository;
import com.fatfa.model.repository.IUsuariosEmpresaRepository;
import com.fatfa.model.service.ISolicitudUsuarioEmpresaService;

@Service
public class SolicitudUsuarioEmpresaServiceImp implements ISolicitudUsuarioEmpresaService {

	private static final Logger log = LoggerFactory.getLogger(SolicitudUsuarioEmpresaServiceImp.class);

	@Autowired
	private ISolicitudUsuarioEmpresaRepository repoSolicitud;

	@Autowired
	private IUsersRepository repoUser;

	@Autowired
	private BCryptPasswordEncoder encripto;

	@Autowired
	private IUsuariosEmpresaRepository repoUserEmpresa;

	@Override
	public List<SolicitudesUsuarioEmpresaModel> srvListaSolicitudesUsuarioEmpresa(int idEmpresa,
			String estadoSolicitud) {

		List<SolicitudesUsuarioEmpresaModel> listaData = new ArrayList<>();
		try {
			if (idEmpresa == 0 && estadoSolicitud.compareTo("Todos") == 0) {
				System.out.println(estadoSolicitud);
				listaData = repoSolicitud.findAll();
			}else if (idEmpresa > 0 && estadoSolicitud.compareTo("Todos") == 0) {
				listaData = repoSolicitud.findByEmpresaIdEmpresa(idEmpresa);
			} else {
				listaData = repoSolicitud.findByEmpresaIdEmpresaAndEstadoSolicitud(idEmpresa, estadoSolicitud);
			}

		} catch (Exception e) {
			log.error("ERROR AL LISTAR DATA SOLICITUD => " + e.toString());
			throw e;
		}
		return listaData;
	}

	@Override
	public Map<String, Object> srvCreateUpdateSolicitudUsuarioEmpresa(SolicitudesUsuarioEmpresaModel solicitud) {
		Map<String, Object> response = new HashMap<>();
		
		try {

			// # BUSCAR QUE EL USUARIO A SOLICITAR NO TENGA YA UNA SOLICITUD PENDIENTE
			Optional<SolicitudesUsuarioEmpresaModel> validaSolicitud = repoSolicitud
					.findByEmpresaIdEmpresaAndEmailAndEstadoSolicitud(solicitud.getEmpresa().getIdEmpresa(),
							solicitud.getEmail(), "PENDIENTE");

			if (validaSolicitud.isPresent()) {
				throw new ErrorConflictException("Ya se encuentra una solicitud con estado pendiente para la cuenta <b>"
						+ solicitud.getEmail() + "</b>.");
			}

//			# VALIDAR QUE LA CUENTA NO EXISTA EN LA TABALA USUARIO
			Optional<UsuarioModel> validaCuentaUser = repoUser.findByEmail(solicitud.getEmail());

			if (validaCuentaUser.isPresent()) {
				throw new ErrorConflictException("la cuenta <b>" + solicitud.getEmail()
						+ "</b>, ya se encuentra registrada en nuestra Base de Datos.");
			}

//			# SI TODO ES CORRECTO CRAMOS LA SOLICITUD
			solicitud.setContrasenia(encripto.encode(solicitud.getContrasenia()));
			SolicitudesUsuarioEmpresaModel solicitudCreada = repoSolicitud.save(solicitud);

			response.put("message",
					"La solicitud fue registrada con éxito, espere que la empresa acepte su solicitud. <br> Cuenta registrada: <b>"
							+ solicitudCreada.getEmail() + "</b>");

		} catch (Exception e) {
			log.error("ERROR IN SAVE OR UPDATE SOLICITUD USUARIO EMPRESA => " + e.toString());
			throw e;
		}
		return response;
	}

	@Override
	public SolicitudesUsuarioEmpresaModel srvBuscarSolicitudUsuarioEmpresaID(int idSolicitud) {

		return repoSolicitud.findById(idSolicitud).orElseThrow(
				() -> new ErrorNotFoundException("No se encontro ninguna solicitud con los parametros de busqueda."));
	}

	@Override
	public Map<String, Object> srvAnularSolicitudUsuarioEmpresa(int idSolicitud, String observacion) {
		Map<String, Object> response = new HashMap<>();
		try {
//			# BUSCAMOS LA SOLICITUD A RECHAZAR
			SolicitudesUsuarioEmpresaModel solicitudDB = repoSolicitud.findById(idSolicitud)
					.orElseThrow(() -> new ErrorNotFoundException(
							"No se encontro ninguna solicitud con los parametros de busqueda."));

//			# ACTUALIZAR EL ESTADO Y UN COMENTARIO SI LO UBIERA
			solicitudDB.setObservaciones(observacion);
			solicitudDB.setEstadoSolicitud("ANULADA");
			repoSolicitud.save(solicitudDB);
//			# RETORNAMOS UNA RESPUESTA
			response.put("message",
					"La solicitud para la cuenta <b>" + solicitudDB.getEmail() + "</b> de la empresa <b>"
							+ solicitudDB.getEmpresa().getRazonSocial() + "</b> fue rechazada con éxito.");
		} catch (Exception e) {
			log.error("ERROR IN DESTROY SOLICITUD USUARIO EMPRESA => " + e.toString());
			throw e;
		}
		return response;
	}

	@Override
	public Map<String, Object> srvAprobarSolicitudUsuarioEmpresa(int idSolicitud) {
		Map<String, Object> response = new HashMap<>();
		try {
//			# BUSCAMOS LA SOLICITUD APROBAR
			SolicitudesUsuarioEmpresaModel solicitudDB = repoSolicitud.findById(idSolicitud)
					.orElseThrow(() -> new ErrorNotFoundException(
							"No se encontro ninguna solicitud con los parametros de busqueda."));
//			# CREAMOS LA CUENTA DEL USUARIO
			UsuarioModel usuarioCreado = repoUser.save(new UsuarioModel(solicitudDB.getEmail(),
					solicitudDB.getContrasenia(), solicitudDB.getApellidos() + " " + solicitudDB.getNombre(), true,
					true, new Date(), true, new Date(), 1, solicitudDB.getPerfil()));
//			# CREAMOS EL ANEXO A LA TABLA USUARIO EMPRESA
			repoUserEmpresa.save(new UsuariosEmpresaModel(solicitudDB.getEmpresa(), usuarioCreado));
			solicitudDB.setEstadoSolicitud("APROBADO");
			repoSolicitud.save(solicitudDB);
//			# RETORNAMOS UNA RESPUESTA
			response.put("message",
					"La solicitud para la cuenta <b>" + solicitudDB.getEmail() + "</b> de la empresa <b>"
							+ solicitudDB.getEmpresa().getRazonSocial() + "</b> fue aprovado con éxito,");
		} catch (Exception e) {
			log.error("ERROR IN CHECKOUT SOLICITUD USUARIO EMPRESA => " + e.toString());
			throw e;
		}
		return response;
	}

}
