package com.example.demo.dto.request;

import com.example.demo.entity.Source;

public class ExerciseCreationRequest {
    
    private String name;
    private String Content;
    private String input;
    private String output;
    private Source source;

    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContent() {
        return Content;
    }
    public void setContent(String content) {
        Content = content;
    }
    public String getInput() {
        return input;
    }
    public void setInput(String input) {
        this.input = input;
    }
    public String getOutput() {
        return output;
    }
    public void setOutput(String output) {
        this.output = output;
    }
    public Source getSource() {
        return source;
    }
    public void setSource(Source source) {
        this.source = source;
    }


}
