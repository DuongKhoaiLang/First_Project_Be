package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService{
    private final StudentRepository studentrepository;

    public StudentService(StudentRepository studentRepository){
        this.studentrepository = studentRepository;
    }

    public Student saveStudent(Student student){
        return studentrepository.save(student);
    }

    public void deleteStudent(Integer Id){
        studentrepository.deleteById(Id);
    }

    public List<Student> getAllStudent(){
        return this.studentrepository.findAll();
    }

}
