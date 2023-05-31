package com.fatfa.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.CategoriasModelo;

@Repository
public interface ICategoriasRepository extends JpaRepository<CategoriasModelo, Integer> {
	/**
	 * @author SOPORTE
	 * @apiNote BUSCAR CATEGORIA POR NOMBRE
	 * @param categoriaNombre
	 * @return
	 */
	Optional<CategoriasModelo> findByCategoria(String categoriaNombre);
}
