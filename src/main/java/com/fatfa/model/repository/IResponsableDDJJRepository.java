package com.fatfa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.ResponsableDDJJModel;

@Repository
public interface IResponsableDDJJRepository extends JpaRepository<ResponsableDDJJModel, Integer>{

}
