package com.devathon.slytherin.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.devathon.slytherin.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{
    
}
