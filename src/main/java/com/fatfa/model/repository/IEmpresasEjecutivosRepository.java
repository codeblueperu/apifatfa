package com.fatfa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.EmpresasEjecutivosModel;

@Repository
public interface IEmpresasEjecutivosRepository extends JpaRepository<EmpresasEjecutivosModel, Integer> {

}
