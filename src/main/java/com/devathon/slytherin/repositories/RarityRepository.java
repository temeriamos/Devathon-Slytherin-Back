package com.devathon.slytherin.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.devathon.slytherin.models.RarityModel;

@Repository
public interface RarityRepository extends JpaRepository<RarityModel, Long> {

}
