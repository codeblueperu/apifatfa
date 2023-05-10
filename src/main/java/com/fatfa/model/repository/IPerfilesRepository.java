package com.fatfa.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.PerfilesModel;

@Repository
public interface IPerfilesRepository extends JpaRepository<PerfilesModel, Integer> {

	/**
	 * @author CodeBluePeru
	 * @apiNote SELECT * FORM TB_PERFILES WHERE DESCRIPCION = ?1
	 * @param namePerfil
	 * @return
	 */
	Optional<PerfilesModel> findByDescripcion(String namePerfil);
}
