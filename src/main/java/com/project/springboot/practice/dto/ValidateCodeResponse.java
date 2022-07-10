package com.project.springboot.practice.dto;

import lombok.Data;

@Data
public class ValidateCodeResponse {

    private String status;
    private String message;

    public ValidateCodeResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
