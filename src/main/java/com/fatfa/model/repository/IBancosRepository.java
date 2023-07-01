package com.fatfa.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.BancosModel;

@Repository
public interface IBancosRepository extends JpaRepository<BancosModel, String> {

}
