package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Source;
import com.example.demo.repository.SourceRepository;

@Service
public class SourceService {
    @Autowired
    private SourceRepository sourceRepository;

    public Source addSource(Source source){
        return sourceRepository.save(source);
    }

    public List<Source> getAllSource(){
        return sourceRepository.findAll();
    }

    public Source getSourceById(String id){
        return sourceRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    public void updateSource(String id,Source source){
        Source sou = getSourceById(id);
        sou.setExercise(source.getExercise());
        sou.setName(source.getName());

    }

    public void deleteSoureById(String id){
        sourceRepository.deleteById(id);
    }
}
