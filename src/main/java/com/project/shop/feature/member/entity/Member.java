package com.project.shop.feature.member.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Member {
    private int idx;
    private String memberID;
    private String password;
    private String name;
    private String birth;
    private String mobile;
    private String mail;
    private String address;
    private Date createDate;
    private Date updateDate;
}
