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

    public Teacher saveTeacher(Teacher teacher){
        return this.teacherRepository.save(teacher);
    }

    public void deleteTeacherById(int id){
        this.teacherRepository.deleteById(id);
    }
}
