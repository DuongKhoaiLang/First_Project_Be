package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Source;


@Repository
public interface SourceRepository extends JpaRepository<Source,String>{
    boolean existsByName(String name);
}
