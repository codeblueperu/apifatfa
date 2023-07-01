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
import org.springframework.stereotype.Service;

import com.fatfa.model.entity.TransferenciaModel;
import com.fatfa.model.repository.ITransferenciaRepository;
import com.fatfa.model.service.ITransferenciaService;
import com.fatfa.utils.Constantes;

@Service
public class TransferenciaServiceImp implements ITransferenciaService {

	private static final Logger log = LoggerFactory.getLogger(TransferenciaServiceImp.class);

	@Autowired
	private ITransferenciaRepository repoTransferencias;

	@Override
	public Map<String, Object> srvAgregarTransferencia(TransferenciaModel datos) {

		Map<String, Object> map = new HashMap<>();

		try {
			repoTransferencias.save(datos);
			map.put("message", "Transferencia registrado con éxito");
		} catch (Exception e) {
			log.error("ERROR GUARDAR TRANSFERENCIA => " + e.toString());
			throw e;
		}
		return map;
	}

	@Override
	public List<TransferenciaModel> srvListarTransaferencia(int idEmpresa, String estado, Date fecha) {
		List<TransferenciaModel> transferencias = new ArrayList<>();
		System.out.println(fecha);
		System.out.println(new Date());
		try {
			if (idEmpresa == 0 && estado.compareTo("Todos") == 0 && Constantes.utilFormatoFecha(fecha, "yyyy-MM-dd").compareTo(Constantes.utilFormatoFecha(new Date(), "yyyy-MM-dd")) == 0) {
				transferencias = repoTransferencias.findAll();
			} else if (idEmpresa > 0 && estado.compareTo("Todos") == 0 && Constantes.utilFormatoFecha(fecha, "yyyy-MM-dd").compareTo(Constantes.utilFormatoFecha(new Date(), "yyyy-MM-dd")) == 0) {
				transferencias = repoTransferencias.findByEmpresaIdEmpresa(idEmpresa);
			} else if (idEmpresa > 0 && estado.compareTo("Todos") == 1  && Constantes.utilFormatoFecha(fecha, "yyyy-MM-dd").compareTo(Constantes.utilFormatoFecha(new Date(), "yyyy-MM-dd")) == 0){
				transferencias = repoTransferencias.findByEmpresaIdEmpresaAndEstadoSolicitud(idEmpresa, estado);
			} else {
				transferencias = repoTransferencias.findByEmpresaIdEmpresaAndEstadoSolicitudAndFechaPago(idEmpresa, estado, fecha);
			}

		} catch (Exception e) {
			log.error("ERROR AL LISTAR TRANSFERENCIA => " + e.toString());
			throw e;
		}
		return transferencias;
	}

	@Override
	public TransferenciaModel srvBuscraComprobante(Integer idTransferencia) {
		Optional<TransferenciaModel> newdatos= repoTransferencias.findById(idTransferencia) ;
		if(newdatos.isPresent()) {
			return newdatos.get();
		}else {
			return null;
		}
	}

}
