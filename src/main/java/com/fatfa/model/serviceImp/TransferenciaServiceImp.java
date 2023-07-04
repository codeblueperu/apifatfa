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

import com.fatfa.exceptions.ErrorNotFoundException;
import com.fatfa.model.entity.EstadoPagoModel;
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
	public List<TransferenciaModel> srvListarTransaferencia(int idEmpresa, int estado, Date fecha, Date fechaF) {
		List<TransferenciaModel> transferencias = new ArrayList<>();
		try {
			if (idEmpresa == 0 && estado == 0 && Constantes.utilFormatoFecha(fecha, "yyyy-MM-dd").compareTo(Constantes.utilFormatoFecha(new Date(), "yyyy-MM-dd")) == 0) {
				transferencias = repoTransferencias.findAll();
			} else if (idEmpresa > 0 && estado == 0 && Constantes.utilFormatoFecha(fecha, "yyyy-MM-dd").compareTo(Constantes.utilFormatoFecha(new Date(), "yyyy-MM-dd")) == 0) {
				transferencias = repoTransferencias.findByEmpresaIdEmpresa(idEmpresa);
			} else if (idEmpresa > 0 && estado == 0  && Constantes.utilFormatoFecha(fecha, "yyyy-MM-dd").compareTo(Constantes.utilFormatoFecha(new Date(), "yyyy-MM-dd")) == 0){
				transferencias = repoTransferencias.findByEmpresaIdEmpresaAndEstadoPagoIdEstadoPago(idEmpresa, estado);
			} else {
				transferencias = repoTransferencias.findByEmpresaIdEmpresaAndEstadoPagoIdEstadoPagoAndFechaPagoBetween(idEmpresa, estado, fecha, fechaF);
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

	@Override
	public Map<String, Object> srvUpdateTransferencias(int idTransferencias, int estadoPago) {
		Map<String, Object> map = new HashMap<>();
		System.out.println(estadoPago);
		try {
			TransferenciaModel transferencia = repoTransferencias.findById(idTransferencias).orElseThrow(()-> new ErrorNotFoundException("No se encontro ningun registro con el ID enviado."));
			transferencia.setEstadoPago(new EstadoPagoModel(estadoPago));
			repoTransferencias.save(transferencia);
			if(estadoPago == 2) {
				map.put("message","La transferencia fue <b>confirmada</b> con éxito.");
			}else {
				map.put("message","La transferencia fue <b>anulada</b> con éxito.");
			}
			
		} catch (Exception e) {
			log.error("ERROR AL MODIFICAR ESTADO DE TRANSFERENCIA => " + e.toString());
			throw e;
		}
		
		return map;
	}

}
