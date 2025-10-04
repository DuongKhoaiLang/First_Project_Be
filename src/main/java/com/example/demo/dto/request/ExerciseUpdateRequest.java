package com.example.demo.dto.request;

public class ExerciseUpdateRequest {
    private String Content;
    private String input;
    private String output;

    
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
    
    
}
