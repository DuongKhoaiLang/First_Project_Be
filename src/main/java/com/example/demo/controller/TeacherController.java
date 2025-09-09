package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Teacher;
import com.example.demo.service.TeacherService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<Teacher> getAllTeacher(){
        return teacherService.getAllTeacher();
    }

    @GetMapping("/{id}")
    public Teacher getTeacherByIdTeacher(@PathVariable int id) {
        return teacherService.getTeacherById(id);
    }

    @GetMapping("/search/by-income")
    public List<Teacher> getByGreaterThan(@RequestParam(defaultValue = "1",name = "dk") int income) {
        return teacherService.findByIncomeGreaterThan(income);
    }
    
    @GetMapping("/search/by-name")
    public Teacher getByNameTeacher(@RequestParam(name = "name") String name_Teacher){
        return teacherService.findByNameTeacher(name_Teacher);
    }

    @GetMapping("/search/by-role")
    public List<Teacher> getByRoleTeacher(@RequestParam(name = "role") String Role){
        return teacherService.findByRole(Role);
    }
    
    @PostMapping
    public Teacher getTeacherById(@RequestBody Teacher teacher){
        return teacherService.saveTeacher(teacher);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacherById(@PathVariable int id){
        teacherService.deleteTeacherById(id);
    }

    @PutMapping("/{id}")
    public Teacher updateTeacherById(@PathVariable int id, @RequestBody Teacher entity) {
        return teacherService.updateTeacher(id, entity);
    }
}