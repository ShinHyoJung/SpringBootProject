package com.project.springboot.practice.springbootproject.dto;

public class ValidateCodeResponse {

    String status;
    String message;

    public ValidateCodeResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
