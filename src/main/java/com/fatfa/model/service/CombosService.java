package com.fatfa.model.service;

import java.util.List;

import com.fatfa.model.entity.ActividadesEconomicaModel;
import com.fatfa.model.entity.CategoriasModelo;
import com.fatfa.model.entity.ConveniosAplicablesModel;
import com.fatfa.model.entity.EstadoTrabajadoresModel;
import com.fatfa.model.entity.LocalidadModel;
import com.fatfa.model.entity.PartidosModel;
import com.fatfa.model.entity.ProvinciasModel;
import com.fatfa.model.entity.SindicatosModel;
import com.fatfa.model.entity.ZonasModel;

public interface CombosService {
	/**
	 * @author CodeBluePeru
	 * @apiNote LISTA TODAS LAS PROVINCIAS DE LA BASE DE DATOS
	 * @return
	 */
	List<ProvinciasModel> srvProvincias();
	/**
	 * @author CodeBluePeru
	 * @apiNote LISTA TODAS LOS PARTIDOS DE LA BASE DE DATOS
	 * @return
	 */
	List<PartidosModel> srvPartidos(ProvinciasModel IdProvincia);
	/**
	 * @author CodeBluePeru
	 * @apiNote LISTA TODAS LAS LOCALIDADES DE LA BASE DE DATOS
	 * @return
	 */
	List<LocalidadModel> srvLocalidad(ProvinciasModel IdProvincia, PartidosModel Partidos);

	/**
	 * @author CodeBluePeru
	 * @apiNote LISTA TODAS LAS ACTIVIDADES ECONOMICAS DE LA BASE DE DATOS
	 * @return
	 */
	List<ActividadesEconomicaModel> srvActividadEconomica();
	
	/**
	 * @author CodeBluePeru
	 * @apiNote LISTA TODAS LOS CONVENIOS APLICABLES DE LA BASE DE DATOS
	 * @return
	 */
	List<ConveniosAplicablesModel> srvConvenioAplicable();
	
	/**
	 * @author CodeBluePeru
	 * @apiNote LISTA TODAS LAS ZONAS DE LA BASE DE DATOS
	 * @return
	 */
	List<ZonasModel> srvZonas();
	
	/**
	 * @author CodeBluePeru
	 * @apiNote LISTA TODAS LAS CATEGORIAS DE LA BASE DE DATOS
	 * @return
	 */
	List<CategoriasModelo> srvCategorias();
	
	/**
	 * @author CodeBluePeru
	 * @apiNote LISTA TODAS LOS ESTADOS DE TRABAJADORES DE LA BASE DE DATOS
	 * @return
	 */
	List<EstadoTrabajadoresModel> srvEstado();
	
	/**
	 * @author CodeBluePeru
	 * @apiNote LISTA TODAS LOS SINDICATOS DE LA BASE DE DATOS
	 * @return
	 */
	List<SindicatosModel> srvSindicato();
	
	
}
