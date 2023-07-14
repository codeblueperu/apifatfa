package com.fatfa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.CorrelativosDocumentosFatfa;


@Repository
public interface ICorrelativoDocumentosFatfaDao extends JpaRepository<CorrelativosDocumentosFatfa, Integer> {

}
