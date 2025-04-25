package com.devathon.slytherin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devathon.slytherin.models.HouseModel;

public interface HouseRepository  extends JpaRepository<HouseModel, Long>{

}
