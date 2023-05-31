package com.fatfa.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.EmpresasModel;

@Repository
public interface IEmpresaRepository extends JpaRepository<EmpresasModel, Integer> {

	/**
	 * @author CodeBluePeru
	 * @apiNote BUSCAR EMPRESA POR NOMBRE
	 * @param nombre
	 * @return
	 */
	Optional<EmpresasModel> findByCuitOrRazonSocial(String nombre, String razonsocial);
	
	/**
	 * @author SOPORTE
	 * @apiNote BUSCAR EMPRESA POR CUIT
	 * @param cuit
	 * @return
	 */
	Optional<EmpresasModel> findByCuit(String cuit);

}
