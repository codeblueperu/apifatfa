package com.fatfa.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.TransferenciaModel;

@Repository
public interface ITransferenciaRepository extends JpaRepository<TransferenciaModel, Integer> {

	/**
	 * @author CodeBluePeru
	 * @param idEmpresa
	 * @return
	 */
	List<TransferenciaModel> findByEmpresaIdEmpresa(int idEmpresa);
	/**
	 * @author CodeBluePeru
	 * @param idEmpresa
	 * @param estadoSolicitud
	 * @return
	 */
	
	List<TransferenciaModel> findByEmpresaIdEmpresaAndEstadoSolicitud(int idEmpresa, String estadoSolicitud);
	
	/**
	 * @author CodeBluePeru
	 * @param idEmpresa
	 * @param estadoSolicitud
	 * @param fecha
	 * @return
	 */
	
	List<TransferenciaModel> findByEmpresaIdEmpresaAndEstadoSolicitudAndFechaPago(int idEmpresa, String estadoSolicitud, Date fecha);
}
