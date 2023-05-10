package com.fatfa.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.UsuarioModel;

@Repository
public interface IUsersRepository extends JpaRepository<UsuarioModel, Integer> {

	/**
	 * @author CodeBluePeru
	 * @apiNote BUSCAR USUARIO EMAIL LOGIN
	 * @param emial
	 * @return
	 */
	Optional<UsuarioModel> findByEmail(String emial);
	
	
}
