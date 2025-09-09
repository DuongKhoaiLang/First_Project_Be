package com.example.demo.service;


import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Teacher;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getAllTeacher(){
        return this.teacherRepository.findAll();
    }

    public Teacher getTeacherById(int id){
        return this.teacherRepository.findById(id).orElse(null);
    }

    public Teacher saveTeacher(Teacher teacher){
        return this.teacherRepository.save(teacher);
    }

    public void deleteTeacherById(int id){
        this.teacherRepository.deleteById(id);
    }

    public Teacher updateTeacher(int id,Teacher teacher_Update){
        return this.teacherRepository.findById(id).map( teacher -> {
            teacher.setName_Teacher(teacher_Update.getName_Teacher());
            teacher.setIncome_Teacher((int)teacher_Update.getIncome_Teacher());
            teacher.setTeacher_Falcuty(teacher_Update.getTeacher_Falcuty());
            return teacherRepository.save(teacher);
        }).orElseThrow(() -> new RuntimeException("teacher not found"));
    }

    public List<Teacher> findByIncomeGreaterThan(int income){
        return this.teacherRepository.findByGreaterThan(income);
    }

    public Teacher findByNameTeacher(String name){
        return this.teacherRepository.findByName(name);
    }

    public List<Teacher> findByRole(String major){
        return this.teacherRepository.findByRole(major);
    }
}
