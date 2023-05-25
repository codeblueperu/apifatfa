package com.fatfa.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.LocalidadModel;
import com.fatfa.model.entity.PartidosModel;
import com.fatfa.model.entity.ProvinciasModel;

@Repository
public interface ILocalidadRepository extends JpaRepository<LocalidadModel, Integer> {

	/**
	 * @author CodeBluePeru
	 * @apiNote BUSCAR LOCALIDAD POR CODIGO DE PROVINCIA Y CODIGO DE PARTIDOS
	 * @param IdProvincia
	 * @param Partidos
	 * @return
	 */
	List<LocalidadModel> findByProvinciaAndPartido(ProvinciasModel IdProvincia, PartidosModel Partidos);
}
