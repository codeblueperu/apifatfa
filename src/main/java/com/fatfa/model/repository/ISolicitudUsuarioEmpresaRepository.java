package com.fatfa.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.SolicitudesUsuarioEmpresaModel;

@Repository
public interface ISolicitudUsuarioEmpresaRepository extends JpaRepository<SolicitudesUsuarioEmpresaModel, Integer> {

	/**
	 * @author CodeBluePeru
	 * @apiNote SELECT * FROM SolicitudesUsuarioEmpresaModel WHERE idEmpresa = ?1
	 * @param idEmpresa
	 * @return
	 */
	List<SolicitudesUsuarioEmpresaModel> findByEmpresaIdEmpresa(int idEmpresa);
	
	/**
	 * @author CodeBluePeru
	 * @apiNote SELECT * FROM SolicitudesUsuarioEmpresaModel WHERE idEmpresa = ?1 AND estadoSolicitud = ?2
	 * @param idEmpresa
	 * @param estadoSolicitud
	 * @return
	 */
	List<SolicitudesUsuarioEmpresaModel> findByEmpresaIdEmpresaAndEstadoSolicitud(int idEmpresa, String estadoSolicitud);
	
	/**
	 * @author CodeBluePeru
	 * @apiNote SELECT * FROM SolicitudesUsuarioEmpresaModel WHERE idEmpresa = ?1 AND email = ?2 AND estadoSolicitud = ?3
	 * @param email
	 * @return
	 */
	Optional<SolicitudesUsuarioEmpresaModel> findByEmpresaIdEmpresaAndEmailAndEstadoSolicitud(int idEmpresa,String email, String estadoSolicitud);
}
