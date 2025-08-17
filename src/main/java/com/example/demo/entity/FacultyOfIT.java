package com.example.demo.entity;

import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "falcutyOfIT")
public class FacultyOfIT {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Falcuty")
    private int id_Falcuty;

    @Column(name = "name_Falcuty")
    private String name_Falcuty;

    @Column(name = "size_Falcuty")
    private int size_Falcuty;

    @OneToMany(mappedBy = "falcuty", cascade = CascadeType.ALL)
    private List<Student> student;

    public int getId_Falcuty() {
        return id_Falcuty;
    }

    public void setId_Falcuty(int id_Falcuty) {
        this.id_Falcuty = id_Falcuty;
    }

    public String getName_Falcuty() {
        return name_Falcuty;
    }

    public void setName_Falcuty(String name_Falcuty) {
        this.name_Falcuty = name_Falcuty;
    }

    public int getSize_Falcuty() {
        return size_Falcuty;
    }

    public void setSize_Falcuty(int size_Falcuty) {
        this.size_Falcuty = size_Falcuty;
    }

    
}
