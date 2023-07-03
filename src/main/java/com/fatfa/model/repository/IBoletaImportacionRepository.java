package com.fatfa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.BoletasImportacionModel;

@Repository
public interface IBoletaImportacionRepository extends JpaRepository<BoletasImportacionModel, Integer> {

}
