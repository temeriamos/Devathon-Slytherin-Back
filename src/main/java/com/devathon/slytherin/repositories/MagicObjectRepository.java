package com.devathon.slytherin.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devathon.slytherin.models.MagicObjectModel;

@Repository
public interface MagicObjectRepository extends JpaRepository<MagicObjectModel, Long> {

    Page<MagicObjectModel> findByCategoryName(String categoryName, Pageable pageable);

    Page<MagicObjectModel> findByPurchased(boolean purchased, Pageable pageable);

    Page<MagicObjectModel> findByCategory_Id(Long categoryId, Pageable pageable); // Método para buscar por ID de categoría

    Page<MagicObjectModel> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<MagicObjectModel> findByCategory_IdAndPurchased(Long categoryId, boolean purchased, Pageable pageable);

    List<MagicObjectModel> findByRarity_Id(Long rarityId); // Método para buscar por ID de rareza

    Page<MagicObjectModel> findByCategory_IdAndPurchasedAndRarity_IdNot(Long categoryId, boolean purchased, Long rarityId, Pageable pageable);

    Page<MagicObjectModel> findByRarity_IdNot(Long rarityId, Pageable pageable);

    Page<MagicObjectModel> findByNameContainingIgnoreCaseAndPurchasedAndRarity_IdNot(String name, boolean purchased, Long rarityId, Pageable pageable);
}
