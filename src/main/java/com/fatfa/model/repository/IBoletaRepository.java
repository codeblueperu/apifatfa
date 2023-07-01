package com.fatfa.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.BoletaModel;

@Repository
public interface IBoletaRepository extends JpaRepository<BoletaModel, Integer> {

	/**
	 * @author SOPORTE
	 * @apiNote BUSCAR BOLETA SEGUN ANIO Y MES y empresa
	 * @param mesPeriodo
	 * @param anioPeriodo
	 * @return
	 */
	Optional<BoletaModel> findByMesAndAnioAndEmpresaIdEmpresaAndEstadoPagoIdEstadoPago(String mesPeriodo, String anioPeriodo, int idEmpresa, int idEstadoPago);
	
	/**
	 * @author SOPORTE
	 * @apiNote BUSCAR BOLETA SEGUN ANIO Y MES y empresa
	 * @param mesPeriodo
	 * @param anioPeriodo
	 * @return
	 */
	List<BoletaModel> findByEmpresaIdEmpresaAndAporteSindicalIdAporteAndMesAndAnio(int idEmpresa, int idAporte, String mesPeriodo, String anioPeriodo);
}
