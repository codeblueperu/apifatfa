package com.fatfa.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.SindicatosModel;

@Repository
public interface ISindicatoRepository extends JpaRepository<SindicatosModel, Integer> {
	/**
	 * @author SOPORTE
	 * @apiNote BUSCAR SINDICATO POR NOMBRE
	 * @param nombre_sindicato
	 * @return
	 */
	Optional<SindicatosModel> findByNombreSindicato(String nombre_sindicato);
}
