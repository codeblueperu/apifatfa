package com.fatfa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.UsuariosEmpresaModel;

@Repository
public interface IUsuariosEmpresaRepository extends JpaRepository<UsuariosEmpresaModel, Integer> {

}
