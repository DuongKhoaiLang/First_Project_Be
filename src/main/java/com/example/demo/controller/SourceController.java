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

import com.example.demo.entity.Source;
import com.example.demo.service.SourceService;

@RestController
@RequestMapping("/source")
public class SourceController {
    @Autowired
    private SourceService sourceService;

    @GetMapping
    public List<Source> getAllSources(){
        return sourceService.getAllSource();
    }

    @GetMapping("/{id}")
    public Source getSourceById(@PathVariable String id){
        return sourceService.getSourceById(id);
    }

    @PostMapping
    public void addSource(@RequestBody Source source){
        sourceService.addSource(source);
    }

    @PutMapping("/{id}")
    public void updateSource(@PathVariable String id,@RequestBody Source source){
        sourceService.updateSource(id, source);
    }

    @DeleteMapping("/{id}")
    public void deleteSoureById(@PathVariable String id){
        sourceService.deleteSoureById(id);  
    }

}
