package com.fatfa.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.TablaSueldoBasicoModel;

@Repository
public interface ISueldoBasicoRepository extends JpaRepository<TablaSueldoBasicoModel, Integer> {
	
	/**
	 * @author SOPORTE
	 * @apiNote OBETENR EL SUELDO DEL TRABAJADOR SEGUN SEA EL PERIODO
	 * @param idCategoriaTrabajador
	 * @param perido
	 * @return
	 */
	Optional<TablaSueldoBasicoModel>	 findByIdCategoriaAndPeriodo(String idCategoriaTrabajador, String perido);

}
