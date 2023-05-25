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
import org.springframework.stereotype.Service;

import com.fatfa.exceptions.ErrorConflictException;
import com.fatfa.exceptions.ErrorNotFoundException;
import com.fatfa.model.entity.DomicilioModel;
import com.fatfa.model.entity.DomiciliosEmpresaModel;
import com.fatfa.model.entity.EmpresasModel;
import com.fatfa.model.entity.ResponsableDDJJModel;
import com.fatfa.model.entity.ResponsableRRHHModel;
import com.fatfa.model.repository.IDomicilioRepository;
import com.fatfa.model.repository.IDomiciliosEmpresaRepository;
import com.fatfa.model.repository.IEmpresaRepository;
import com.fatfa.model.repository.IResponsableDDJJRepository;
import com.fatfa.model.repository.IResponsableRRHHRepository;
import com.fatfa.model.service.IEmpresaService;

@Service
public class EmpresaServiceImpl implements IEmpresaService {
	private static final Logger log = LoggerFactory.getLogger(EmpresaServiceImpl.class);

	@Autowired
	private IEmpresaRepository repoEmpresa;

	@Autowired
	private IDomicilioRepository repoDomicilio;
	
	@Autowired
	private IResponsableDDJJRepository repoResDDJJ;
	
	@Autowired
	private IResponsableRRHHRepository repoResRRHH;
	
	@Autowired
	private IDomiciliosEmpresaRepository repoDomicilioEmpresa;

	@Override
	public List<EmpresasModel> srvListaEmpresas() {
		List<EmpresasModel> listEmpresa = new ArrayList<>();
		try {
			listEmpresa = repoEmpresa.findAll();
		} catch (Exception e) {
			log.error("ERROR LISTAR EMPRESAS => " + e.toString());
			throw e;
		}
		return listEmpresa;
	}

	@Override
	public Map<String, Object> srvEliminarEmpresas(int idEmpresa) {
		Map<String, Object> map = new HashMap<>();
		try {
			EmpresasModel empresa = new EmpresasModel();
			empresa = repoEmpresa.findById(idEmpresa).orElseThrow(
					() -> new ErrorConflictException("No se puede realizar una eliminacion con un ID que no éxiste."));
			map.put("message", "La Empresa <b>" + empresa.getNombreFantasia() + "</b> fue eliminado con éxito");
		} catch (Exception e) {
			log.error("ERROR ELIMINAR EMPRESA => " + e.toString());
			throw e;
		}
		return null;
	}

	@Override
	public EmpresasModel srvBuscarEmpresaID(int idEmpresa) {
		return repoEmpresa.findById(idEmpresa)
				.orElseThrow(() -> new ErrorNotFoundException("No se encontro ninguna empresa con el ID " + idEmpresa));
	}

	@Override
	public EmpresasModel srvBuscarEmpresaNombre(String nombre, String razonsocial) {
		EmpresasModel empresa = new EmpresasModel();
		try {
			empresa = repoEmpresa.findByCuitOrRazonSocial(nombre, razonsocial)
					.orElseThrow(() -> new ErrorNotFoundException(
							"El nombre <b>" + nombre + "</b>, no se encuentra resgistrado en nuestra base de datos."));
		} catch (Exception e) {
			log.error("ERROR BUSCAR EMPRESA => " + e.toString());
			throw e;
		}
		return empresa;
	}

	@Override
	@Transactional
	public Map<String, Object> srvAgregarEmpresa(EmpresasModel empresa, List<DomicilioModel> domicilio,
			ResponsableDDJJModel responsableDDJJ, ResponsableRRHHModel responsableRRHH) {
		Map<String, Object> map = new HashMap<>();
		try {
			EmpresasModel datosempresa= new EmpresasModel();
			
			
			Optional<EmpresasModel> buscar=repoEmpresa.findByCuitOrRazonSocial(empresa.getCuit(), empresa.getRazonSocial());
			if(buscar.isPresent()) {
				throw new ErrorConflictException("El Nombre <b>" + empresa.getCuit() +"</b> ó la Razón Social <b>"+ empresa.getRazonSocial()
				+ "</b>, ya se encuentra registrado en nuestra Base de Datos");
			}else {
				ResponsableDDJJModel respoDDJJ= new ResponsableDDJJModel();
				ResponsableRRHHModel respoRRHH = new ResponsableRRHHModel();
				respoDDJJ= repoResDDJJ.save(responsableDDJJ);
				respoRRHH=repoResRRHH.save(responsableRRHH);
				empresa.setReponsableDJ(respoDDJJ);
				empresa.setResponableRH(respoRRHH);
				datosempresa=repoEmpresa.save(empresa);
				for (int i = 0; i < domicilio.size(); i++) {
					DomicilioModel datosDomicilio= new DomicilioModel();
					datosDomicilio=repoDomicilio.save(domicilio.get(i));
					DomiciliosEmpresaModel DomicilioEmpresa = new DomiciliosEmpresaModel();
					DomicilioEmpresa.setEmpresa(datosempresa);
					DomicilioEmpresa.setDomicilio(datosDomicilio);
					DomicilioEmpresa.setEstado(true);
					DomicilioEmpresa=repoDomicilioEmpresa.save(DomicilioEmpresa);
					System.out.println(domicilio.get(i));
				}
			}
			map.put("data",datosempresa);
			map.put("message", "La empresa  fue registrado con éxito");
			
		} catch (Exception e) {
			log.error("ERROR GUARDAR EMPRESA => " + e.toString());
			throw e;
		}
		return map;
	}

}
