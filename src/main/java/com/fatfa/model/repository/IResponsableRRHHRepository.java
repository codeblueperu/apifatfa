package com.fatfa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatfa.model.entity.ResponsableRRHHModel;

@Repository
public interface IResponsableRRHHRepository extends JpaRepository<ResponsableRRHHModel, Integer> {

}
