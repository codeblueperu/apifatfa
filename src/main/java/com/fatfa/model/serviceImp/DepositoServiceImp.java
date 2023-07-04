package com.fatfa.model.serviceImp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatfa.exceptions.ErrorNotFoundException;
import com.fatfa.model.entity.DepositosModel;
import com.fatfa.model.entity.EstadoPagoModel;
import com.fatfa.model.repository.IDepositoRepository;
import com.fatfa.model.service.DepositoService;

@Service
public class DepositoServiceImp implements DepositoService {

	private static final Logger log = LoggerFactory.getLogger(DepositoServiceImp.class);
	
	@Autowired
	private IDepositoRepository repoDeposito;
	
	@Override
	public List<DepositosModel> srvfindByEmpresaIdEmpresaAndEstadoPagoIdEstadoPagoAndFechaPagoBetween(int empresa,
			int estadoPago, Date fecha, Date fechaF) {
		List<DepositosModel> listado= new ArrayList<>();
		try {
			if(empresa == 0 && estadoPago == 0) {
				listado = repoDeposito.findAll();
			}else if(empresa > 0 && estadoPago == 0) {
				listado = repoDeposito.findByEmpresaIdEmpresa(empresa);
			}else if(empresa > 0 && estadoPago > 0) {
				listado = repoDeposito.findByEmpresaIdEmpresaAndEstadoPagoIdEstadoPago(empresa, estadoPago);
			}else if(empresa == 0 && estadoPago > 0) {
				listado = repoDeposito.findByEstadoPagoIdEstadoPago(estadoPago);
			}else {
				listado = repoDeposito.findByEmpresaIdEmpresaAndEstadoPagoIdEstadoPagoAndFechaPagoBetween(empresa, estadoPago, fecha, fechaF);
			}
		} catch (Exception e) {
			log.error("ERROR AL LISTAR DEPOSITOS => " + e.toString());
			throw e;
		}
		
		return listado;
	}

	@Override
	public Map<String, Object> srvSaveDepositos(DepositosModel depositos) {
		Map<String, Object> map = new HashMap<>();
		
		try {
			DepositosModel datos = new DepositosModel();
			datos = repoDeposito.save(depositos);
			map.put("", datos.toString());
			map.put("message", "El Deposito fue registrado con éxito");
		} catch (Exception e) {
			log.error("ERROR GUARDAR DEPOSITO => " + e.toString());
			throw e;
		}
		return map;
	}

	@Override
	public DepositosModel srvBuscarDepositoID(int idDeposito) {
		DepositosModel depositos = repoDeposito.findById(idDeposito).orElseThrow(()-> new ErrorNotFoundException("No se encontro ningun registro con el ID enviado.")); 
		return depositos;
	}

	@Override
	public Map<String, Object> srvUpdateDepositos(int idDeposito, int estadoPago) {
		Map<String, Object> map = new HashMap<>();
		System.out.println(estadoPago);
		try {
			DepositosModel depositos = repoDeposito.findById(idDeposito).orElseThrow(()-> new ErrorNotFoundException("No se encontro ningun registro con el ID enviado."));
			depositos.setEstadoPago(new EstadoPagoModel(estadoPago));
			repoDeposito.save(depositos);
			if(estadoPago == 2) {
				map.put("message","EL deposito fue <b>confirmada</b> con éxito.");
			}else {
				map.put("message","EL deposito fue <b>anulada</b> con éxito.");
			}
			
		} catch (Exception e) {
			log.error("ERROR AL MODIFICAR ESTADO DE DEPOSITO => " + e.toString());
			throw e;
		}
		
		return map;
	}


}
