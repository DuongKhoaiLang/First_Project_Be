package com.example.demo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Teacher")
    private int id_Teacher;

    @Column(name = "name_Teacher")
    private String name_Teacher;

    @Column(name = "role_Teacher")
    private String role_Teacher;

    @Column(name = "income_Teacher")
    private int income_Teacher;

    public Teacher(){}

    public int getId_Teacher() {
        return id_Teacher;
    }

    public void setId_Teacher(int id_Teacher) {
        this.id_Teacher = id_Teacher;
    }

    public String getName_Teacher() {
        return name_Teacher;
    }

    public void setName_Teacher(String name_Teacher) {
        this.name_Teacher = name_Teacher;
    }

    public String getRole_Teacher() {
        return role_Teacher;
    }

    public void setRole_Teacher(String role_Teacher) {
        this.role_Teacher = role_Teacher;
    }

    public long getIncome_Teacher() {
        return income_Teacher;
    }

    public void setIncome_Teacher(int income_Teacher) {
        this.income_Teacher = income_Teacher;
    }

    
}