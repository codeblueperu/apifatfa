package com.fatfa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.ConveniosAplicablesModel;

@Repository
public interface IConveniosAplicablesRepository extends JpaRepository<ConveniosAplicablesModel, Integer> {

}
