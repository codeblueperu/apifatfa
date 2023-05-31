package com.fatfa.model.serviceImp;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatfa.model.entity.ActividadesEconomicaModel;
import com.fatfa.model.entity.CategoriasModelo;
import com.fatfa.model.entity.ConveniosAplicablesModel;
import com.fatfa.model.entity.EstadoTrabajadoresModel;
import com.fatfa.model.entity.LocalidadModel;
import com.fatfa.model.entity.PartidosModel;
import com.fatfa.model.entity.ProvinciasModel;
import com.fatfa.model.entity.SindicatosModel;
import com.fatfa.model.entity.ZonasModel;
import com.fatfa.model.repository.IActividadesEconomicasRepositoy;
import com.fatfa.model.repository.ICategoriasRepository;
import com.fatfa.model.repository.IConveniosAplicablesRepository;
import com.fatfa.model.repository.IEstadoTrabajadores;
import com.fatfa.model.repository.ILocalidadRepository;
import com.fatfa.model.repository.IPartidosRepository;
import com.fatfa.model.repository.IProvinciasRepository;
import com.fatfa.model.repository.ISindicatoRepository;
import com.fatfa.model.repository.IZonasRepository;
import com.fatfa.model.service.CombosService;

@Service
public class CombosServiceImpl implements CombosService {

	private static final Logger log = LoggerFactory.getLogger(CombosServiceImpl.class);

	@Autowired
	private IProvinciasRepository repoPro;
	
	@Autowired
	private IPartidosRepository repoPartido;
	
	@Autowired
	private ILocalidadRepository repoLocalidad;
	
	@Autowired
	private IActividadesEconomicasRepositoy repoActividades;
	
	@Autowired
	private IConveniosAplicablesRepository repoConvenios;
	
	@Autowired
	private IZonasRepository repoZonas;
	
	@Autowired
	private ICategoriasRepository repoCategoria;
	
	@Autowired
	private IEstadoTrabajadores repoEstado;
	
	@Autowired
	private ISindicatoRepository repoSindicato;
	
	@Override
	public List<ProvinciasModel> srvProvincias() {
		List<ProvinciasModel> datos= new ArrayList<>();
		try {
			datos = repoPro.findAll();
		} catch (Exception e) {
			log.error("ERROR LISTAR PROVINCIAS => " + e.toString());
			throw e;
		}
		return datos;
	}


	@Override
	public List<ActividadesEconomicaModel> srvActividadEconomica() {
		List<ActividadesEconomicaModel> datos= new ArrayList<>();
		try {
			datos = repoActividades.findAll();
		} catch (Exception e) {
			log.error("ERROR LISTAR ACTIVIDADES ECONOMICAS => " + e.toString());
			throw e;
		}
		return datos;
	}

	@Override
	public List<ConveniosAplicablesModel> srvConvenioAplicable() {
		List<ConveniosAplicablesModel> datos= new ArrayList<>();
		try {
			datos = repoConvenios.findAll();
		} catch (Exception e) {
			log.error("ERROR LISTAR CONVENIOS APLICABLES => " + e.toString());
			throw e;
		}
		return datos;
	}


@Override
public List<PartidosModel> srvPartidos(ProvinciasModel IdProvincia) {
	List<PartidosModel> datos= new ArrayList<>();
	try {
		datos = repoPartido.findByProvincia(IdProvincia);
	} catch (Exception e) {
		log.error("ERROR LISTAR PARTIDOS => " + e.toString());
		throw e;
	}
	return datos;
}


@Override
public List<LocalidadModel> srvLocalidad(ProvinciasModel IdProvincia, PartidosModel Partidos) {
	List<LocalidadModel> datos= new ArrayList<>();
	try {
		datos = repoLocalidad.findByProvinciaAndPartido(IdProvincia, Partidos);
	} catch (Exception e) {
		log.error("ERROR LISTAR LOCALIDADES => " + e.toString());
		throw e;
	}
	return datos;
}


@Override
public List<ZonasModel> srvZonas() {
	List<ZonasModel> datos= new ArrayList<>();
	try {
		datos = repoZonas.findAll();
	} catch (Exception e) {
		log.error("ERROR LISTAR ZONAS => " + e.toString());
		throw e;
	}
	return datos;
}


@Override
public List<CategoriasModelo> srvCategorias() {
	List<CategoriasModelo> datos= new ArrayList<>();
	try {
		datos = repoCategoria.findAll();
	} catch (Exception e) {
		log.error("ERROR LISTAR CATEGORIA => " + e.toString());
		throw e;
	}
	return datos;
}


@Override
public List<EstadoTrabajadoresModel> srvEstado() {
	List<EstadoTrabajadoresModel> datos= new ArrayList<>();
	try {
		datos = repoEstado.findAll();
	} catch (Exception e) {
		log.error("ERROR LISTAR ESTADO DE TRABAJADOR => " + e.toString());
		throw e;
	}
	return datos;
}


@Override
public List<SindicatosModel> srvSindicato() {
	List<SindicatosModel> datos= new ArrayList<>();
	try {
		datos = repoSindicato.findAll();
	} catch (Exception e) {
		log.error("ERROR LISTAR SINDICATO => " + e.toString());
		throw e;
	}
	return datos;
}


}
