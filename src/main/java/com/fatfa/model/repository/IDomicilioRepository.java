package com.fatfa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.DomicilioModel;

@Repository
public interface IDomicilioRepository extends JpaRepository<DomicilioModel, Integer> {

}
