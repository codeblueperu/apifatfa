package com.fatfa.model.serviceImp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatfa.model.entity.DeclaradosModel;
import com.fatfa.model.entity.EmpresasModel;
import com.fatfa.model.repository.IDeclaradosRepository;
import com.fatfa.model.service.IDeclaradosService;

@Service
public class DeclaradosServiceImpl implements IDeclaradosService {
	private static final Logger log = LoggerFactory.getLogger(DeclaradosServiceImpl.class);
	@Autowired
	private IDeclaradosRepository repoDeclarados;

	@Override
	public Map<String, Object> srvAgregarDeclarados(DeclaradosModel declarados) {
		Map<String, Object> map = new HashMap<>();
		try {
			DeclaradosModel datos= new DeclaradosModel();
			datos=repoDeclarados.save(declarados);
			
			map.put("data", datos);
			map.put("message", "El Declarado fue registrado con éxito");
		} catch (Exception e) {
			log.error("ERROR GUARDAR DECLARADO => " + e.toString());
			throw e;
		}
		return map;
	}


	@Override
	@Transactional
	public List<DeclaradosModel> srvCopiarDeclarados(List<DeclaradosModel> declarados) {
		List<DeclaradosModel> datos= new ArrayList<>();
		try {
			datos=repoDeclarados.saveAll(declarados);
		} catch (Exception e) {
			log.error("ERROR GUARDAR COPIA DE LA NÓMINA => " + e.toString());
			throw e;
		}
		return datos;
	}

@Override
public List<DeclaradosModel> srvBuscarDeclarados(EmpresasModel empresa, String mes, String anio,
		Integer rectificativa) {
	List<DeclaradosModel> datos = new ArrayList<>();
	try {
		datos=repoDeclarados.findByEmpresaAndMesAndAnioAndRectificativa(empresa, mes, anio, rectificativa);
	} catch (Exception e) {
		log.error("ERROR LISTAR DATOS => " + e.toString());
		throw e;
	}
	return datos;
}


}
