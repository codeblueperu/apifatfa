package com.fatfa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.SindicatosModel;

@Repository
public interface ISindicatosRepository extends JpaRepository<SindicatosModel, Integer> {

}
