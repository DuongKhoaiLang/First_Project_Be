package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,String>{
    public boolean existsByUserName(String userName);

    public boolean existsByUserEmail(String userEmail);

    public Optional<User> findByUserName(String userName);
    
}