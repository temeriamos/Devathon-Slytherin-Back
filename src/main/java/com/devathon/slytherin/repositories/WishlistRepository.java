package com.devathon.slytherin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devathon.slytherin.models.WishlistModel;

@Repository
public interface WishlistRepository extends JpaRepository<WishlistModel, Long> {
}
