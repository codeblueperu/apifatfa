package com.fatfa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.SindicatosModel;

@Repository
public interface ISindicatoRepository extends JpaRepository<SindicatosModel, Integer> {

}
