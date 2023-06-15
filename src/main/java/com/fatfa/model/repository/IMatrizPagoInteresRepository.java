package com.fatfa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.MatrizInterecesModel;

@Repository
public interface IMatrizPagoInteresRepository extends JpaRepository<MatrizInterecesModel, Integer> {

}
