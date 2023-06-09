package com.fatfa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.ProvinciasModel;

@Repository
public interface IProvinciasRepository extends JpaRepository<ProvinciasModel, Integer> {

}
