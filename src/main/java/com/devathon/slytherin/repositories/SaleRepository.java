package com.devathon.slytherin.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.devathon.slytherin.models.SaleModel;

@Repository
public interface SaleRepository extends JpaRepository<SaleModel, Long> {

}
