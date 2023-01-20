package com.project.shop.feature.member.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Member {
    private int idx;
    private String loginID;
    private String password;
    private String name;
    private String birth;
    private String phone;
    private String email;
    private String address;
    private String detailAddress;
    private String zipCode;
    private Date createDate;
    private Date updateDate;
}
