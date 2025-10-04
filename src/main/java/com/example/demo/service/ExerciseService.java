package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.ExerciseCreationRequest;
import com.example.demo.dto.request.ExerciseUpdateRequest;
import com.example.demo.entity.Exercise;
import com.example.demo.repository.ExerciseRepository;

@Service
public class ExerciseService {
    @Autowired
    private ExerciseRepository exerciseRepository;

    public List<Exercise> getAllExercise(){
        return exerciseRepository.findAll();
    }

    public Exercise getExerciseById(String id){
        return exerciseRepository.findById(id).orElseThrow(() -> new RuntimeException("NotFound"));
    }

    public Exercise addExercise(ExerciseCreationRequest exercise){
        Exercise ex = new Exercise();
        ex.setContent(exercise.getContent());
        ex.setInput(exercise.getInput());
        ex.setOutput(exercise.getOutput());
        ex.setName(exercise.getName());
        ex.setSource(exercise.getSource());
        return exerciseRepository.save(ex);
    }

    public void updateExercise(String id, ExerciseUpdateRequest exercise){
        Exercise ex = getExerciseById(id);
        if(ex != null){
            ex.setContent(exercise.getContent());
            ex.setInput(exercise.getInput());
            ex.setOutput(exercise.getOutput());
            exerciseRepository.save(ex);
        }
        else{
            throw(new RuntimeException("Exercise not Found"));
        }
    }


    public void deleteExercise(String id){
        exerciseRepository.deleteById(id);
    }
}
