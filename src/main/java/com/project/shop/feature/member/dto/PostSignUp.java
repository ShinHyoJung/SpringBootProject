package com.project.shop.feature.member.dto;

import com.project.shop.feature.member.entity.Member;
import lombok.Data;

import java.util.Date;

@Data
public class PostSignUp {
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

    public Member toEntity(String password) {
        Member member = new Member();
        member.setIdx(this.idx);
        member.setLoginID(this.loginID);
        member.setPassword(password);
        member.setName(this.name);
        member.setBirth(this.birth);
        member.setPhone(this.phone);
        member.setEmail(this.email);
        member.setAddress(this.address);
        member.setDetailAddress(this.detailAddress);
        member.setZipCode(this.zipCode);
        member.setCreateDate(this.createDate);
        member.setUpdateDate(this.updateDate);

        return member;
    }
}
