package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Exercise;
import com.example.demo.service.ExerciseService;


@RestController
@RequestMapping("exercise")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping
    public List<Exercise> getAllExercises(){
        return exerciseService.getAllExercise();
    }

    @GetMapping("/{id}")
    public Exercise getExerciseById(@PathVariable(name = "id") String id) {
        return exerciseService.getExerciseById(id);
    }

    @PostMapping
    public void addExercise(@RequestBody Exercise exercise){
        exerciseService.addExercise(exercise);
    }

    @PutMapping("/{id}")
    public void updateExercise(@PathVariable String id, @RequestBody Exercise exercise){
        exerciseService.updateExercise(id, exercise);
    }

    @DeleteMapping("/{id}")
    public void deleteExerciseById(@PathVariable String id){
        exerciseService.deleteExercise(id);
    }
}
