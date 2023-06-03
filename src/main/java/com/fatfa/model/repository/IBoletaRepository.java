package com.fatfa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.BoletaModel;

@Repository
public interface IBoletaRepository extends JpaRepository<BoletaModel, Integer> {

}
