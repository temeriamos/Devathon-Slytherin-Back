package com.devathon.slytherin.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devathon.slytherin.models.MagicObjectModel;

@Repository

public interface MagicObjectRepository extends JpaRepository<MagicObjectModel, Long> {

    Page<MagicObjectModel> findByCategoryName(String categoryName, Pageable pageable);

    Page<MagicObjectModel> findByPurchased(boolean purchased, Pageable pageable);
}
