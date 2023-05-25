package com.fatfa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.ActividadesEconomicaModel;

@Repository
public interface IActividadesEconomicasRepositoy extends JpaRepository<ActividadesEconomicaModel, Integer> {

}
