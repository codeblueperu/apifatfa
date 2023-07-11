package com.fatfa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.AcuerdosPagoModel;

@Repository
public interface IAcuerdoPagoDao extends JpaRepository<AcuerdosPagoModel, Integer> {

}
