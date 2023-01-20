package com.project.shop.feature.member.dto;

import lombok.Data;

@Data
public class PostValidateMemberResponse {
    private String code;
    private String message;
    private int idx;
}
