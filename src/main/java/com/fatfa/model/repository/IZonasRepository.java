package com.fatfa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.ZonasModel;

@Repository
public interface IZonasRepository extends JpaRepository<ZonasModel, Integer> {

}
