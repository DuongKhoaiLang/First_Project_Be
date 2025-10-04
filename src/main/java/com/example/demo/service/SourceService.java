package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.SourceUpdateRequest;
import com.example.demo.dto.request.SuorceCreationRequest;
import com.example.demo.entity.Source;
import com.example.demo.repository.SourceRepository;

@Service
public class SourceService {
    @Autowired
    private SourceRepository sourceRepository;

    public Source addSource(SuorceCreationRequest source){
        Source sour = new Source();
        sour.setName(source.getName());
        return sourceRepository.save(sour);
    }

    public List<Source> getAllSource(){
        return sourceRepository.findAll();
    }

    public Source getSourceById(String id){
        return sourceRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    public void updateSource(String id,SourceUpdateRequest source){
        Source sou = getSourceById(id);
        if(sou != null){
            sou.setName(source.getName());
            sourceRepository.save(sou);
        }
    }

    public void deleteSoureById(String id){
        sourceRepository.deleteById(id);
    }
}
