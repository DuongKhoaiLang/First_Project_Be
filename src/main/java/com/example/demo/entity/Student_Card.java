package com.example.demo.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_card")
public class Student_Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_card")
    private int id_Card;

    @Column(name = "major")
    private String major;

    @OneToOne
    @JoinColumn(name = "id_student")
    private Student student;


    public int getId_Card() {
        return id_Card;
    }

    public void setId_Card(int id_Card) {
        this.id_Card = id_Card;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    @Column(name = "k")
    private String k;
    
}
