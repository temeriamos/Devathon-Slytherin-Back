package com.devathon.slytherin.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.devathon.slytherin.models.MagicObjectModel;

@Repository
public interface MagicObjectRepository extends JpaRepository<MagicObjectModel, Long> {
    
}
