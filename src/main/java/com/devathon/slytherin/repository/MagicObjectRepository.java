package com.devathon.slytherin.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.devathon.slytherin.model.MagicObjectModel;

@Repository
public interface MagicObjectRepository extends JpaRepository<MagicObjectModel, Long> {
    
}
