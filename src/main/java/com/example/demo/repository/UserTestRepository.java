package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserforStudying;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserTestRepository extends JpaRepository<UserforStudying,String>{

}