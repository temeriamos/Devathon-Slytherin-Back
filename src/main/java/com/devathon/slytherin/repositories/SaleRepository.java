package com.devathon.slytherin.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.devathon.slytherin.models.SaleModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<SaleModel, Long> {

    @Query("SELECT s FROM SaleModel s WHERE s.user_id = :userId ORDER BY s.date DESC")
    List<SaleModel> findByUserIdOrderByDateDesc(@Param("userId") Long userId);
}
