package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer>{
    @Query("SELECT s FROM Teacher s WHERE s.income_Teacher >= :income")
    public List<Teacher> findByGreaterThan(@Param("age") int income); 

    @Query("SELECT s FROM Teacher s WHERE s.name_Teacher = :name")
    public Teacher findByName(@Param("name") String name);

    @Query("SELECT s FROM Teacher s WHERE s.role_Teacher = :role")
    public List<Teacher> findByRole(@Param("role") String role);
}
