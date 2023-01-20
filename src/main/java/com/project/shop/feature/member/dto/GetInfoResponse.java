package com.project.shop.feature.member.dto;

import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class GetInfoResponse {
    @NonNull
    private int idx;
    @NonNull
    private String memberID;
    @NonNull
    private String name;

    private String birth;
    private String mobile;
    private String mail;
    private String address;
    private String detailAddress;
    private String zipCode;
    private Date createDate;
    private Date updateDate;
}
