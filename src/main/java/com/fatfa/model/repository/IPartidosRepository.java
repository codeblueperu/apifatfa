package com.fatfa.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.PartidosModel;
import com.fatfa.model.entity.ProvinciasModel;

@Repository
public interface IPartidosRepository extends JpaRepository<PartidosModel, Integer> {

	/**
	 * @author CodeBluePeru
	 * @apiNote BUSCAR PARTIDOS POR CODIGO DE PROVINCIA
	 * @param IdProvincia
	 * @return
	 */
	List<PartidosModel> findByProvincia(ProvinciasModel Provincia);
}
