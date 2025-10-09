package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.SourceUpdateRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.request.SourceCreationRequest;
import com.example.demo.entity.Source;
import com.example.demo.service.SourceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/source")
@CrossOrigin("http://localhost:5173/")
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
    public ApiResponse<Source> addSource(@RequestBody @Valid SourceCreationRequest source){
        ApiResponse<Source> apiResponse = new ApiResponse<>();
        apiResponse.setResult(sourceService.addSource(source));
        return apiResponse;
    }

    @PutMapping("/{id}")
    public void updateSource(@PathVariable String id,@RequestBody SourceUpdateRequest source){
        sourceService.updateSource(id, source);
    }

    @DeleteMapping("/{id}")
    public void deleteSoureById(@PathVariable String id){
        sourceService.deleteSoureById(id);  
    }

}
