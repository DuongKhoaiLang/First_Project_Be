package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.SourceUpdateRequest;
import com.example.demo.dto.request.SourceCreationRequest;
import com.example.demo.entity.Source;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.SourceMapper;
import com.example.demo.repository.SourceRepository;

@Service
public class SourceService {
    @Autowired
    private SourceRepository sourceRepository;

    @Autowired
    private SourceMapper sourceMapper;

    public Source addSource(SourceCreationRequest source){
        if (sourceRepository.existsByName(source.getName())) {
            throw(new AppException(ErrorCode.USER_EXISTED));
        }
        Source sour = sourceMapper.toSource(source);
        return sourceRepository.save(sour);
    }

    public List<Source> getAllSource(){
        return sourceRepository.findAll();
    }

    public Source getSourceById(String id){
        return sourceRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
    }

    public void updateSource(String id,SourceUpdateRequest source){
        Source sou = getSourceById(id);
        if(sou != null){
            sourceMapper.updateSource(sou,source);
            sourceRepository.save(sou);
        }
    }

    public void deleteSoureById(String id){
        sourceRepository.deleteById(id);
    }
}
