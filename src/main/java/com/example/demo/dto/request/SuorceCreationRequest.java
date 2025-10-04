package com.example.demo.dto.request;

import jakarta.validation.constraints.Size;

public class SuorceCreationRequest {
    @Size(min = 5,message = "name at least 5 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
