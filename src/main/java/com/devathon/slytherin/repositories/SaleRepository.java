package com.devathon.slytherin.repositories;

import com.devathon.slytherin.models.SaleModel;
import com.devathon.slytherin.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SaleRepository extends JpaRepository<SaleModel, Long> {
    List<SaleModel> findByUserOrderByDateDesc(UserModel user);
}
