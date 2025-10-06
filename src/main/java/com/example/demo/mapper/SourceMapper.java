package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.demo.dto.request.SourceCreationRequest;
import com.example.demo.dto.request.SourceUpdateRequest;
import com.example.demo.entity.Source;

@Mapper(componentModel = "spring")
public interface SourceMapper {

    public Source toSource(SourceCreationRequest sourceCreationRequest);

    public void updateSource(@MappingTarget Source source,SourceUpdateRequest sourceCreationRequest);
}