package com.fatfa.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.ZonasModel;

@Repository
public interface IZonasRepository extends JpaRepository<ZonasModel, Integer> {
	/**
	 * @author SOPORTE
	 * @apiNote BUSCAR ZONA POR SU DESCRIPCION
	 * @param zona
	 * @return
	 */
	Optional<ZonasModel> findByZona(String zona);
}
