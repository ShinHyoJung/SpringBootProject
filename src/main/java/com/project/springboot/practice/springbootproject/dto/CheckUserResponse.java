package com.project.springboot.practice.springbootproject.dto;

import lombok.Data;

@Data
public class CheckUserResponse {
    String username;

    public CheckUserResponse(String username)  {
        this.username = username;
    }

}
