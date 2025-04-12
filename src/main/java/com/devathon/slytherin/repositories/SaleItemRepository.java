package com.devathon.slytherin.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.devathon.slytherin.models.SaleItemModel;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItemModel, Long>{
    List<SaleItemModel> findBySale_Id(Long saleId);
}
