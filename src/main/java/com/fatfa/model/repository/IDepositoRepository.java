package com.fatfa.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.DepositosModel;

@Repository
public interface IDepositoRepository extends JpaRepository<DepositosModel, Integer> {

	/**
	 * @author CodeBluePeru
	 * @param idEmpresa
	 * @param estadoSolicitud
	 * @param fecha
	 * @param fechaF
	 * @return
	 */
	
	List<DepositosModel> findByEmpresaIdEmpresaAndEstadoPagoIdEstadoPagoAndFechaPagoBetween(int idEmpresa, int estadoSolicitud, Date fecha, Date fechaF);
	
	/**
	 * @author CodeBluePeru
	 * @param idEmpresa
	 * @param estadoSolicitud
	 * @return
	 */
	
	List<DepositosModel> findByEmpresaIdEmpresaAndEstadoPagoIdEstadoPago(int idEmpresa, int estadoSolicitud);
	
	/**
	 * @author CodeBluePeru
	 * @param idEmpresa
	 * @return
	 */
	
	List<DepositosModel> findByEmpresaIdEmpresa(int idEmpresa);
	
	/**
	 * @author CodeBluePeru
	 * @param estadoSolicitud
	 * @return
	 */
	
	List<DepositosModel> findByEstadoPagoIdEstadoPago(int estadoSolicitud);
}
