package com.example.demo.service;

import java.lang.classfile.ClassFile.Option;
import java.util.List;
import java.util.Optional;

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

    public Student getStudentById(int id){
        Optional<Student> studentOpt = studentrepository.findById(id);
        return studentOpt.orElse(null);
    }

    public Student updateStudentById(int id,Student student_Update){
        return studentrepository.findById(id).map(student -> {
            student.setNameStudent(student_Update.getNameStudent());
            student.setMajor(student_Update.getMajor());
            student.setFalcuty(student_Update.getFalcuty());
            student.setStudent_Card(student_Update.getStudent_Card());
            return student;
        }).orElseThrow(() -> new RuntimeException("Student not found"));
    }
}
