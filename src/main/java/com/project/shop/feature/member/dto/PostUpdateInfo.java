package com.project.shop.feature.member.dto;

import com.project.shop.feature.member.entity.Member;
import lombok.Data;

@Data
public class PostUpdateInfo {
    private int idx;
    private String name;
    private String password;
    private String birth;
    private String phone;
    private String email;
    private String address;
    private String detailAddress;
    private String zipCode;

    public Member toEntity(String password) {
        Member member = new Member();
        member.setIdx(this.idx);
        member.setName(this.name);
        member.setPassword(password);
        member.setBirth(this.birth);
        member.setPhone(this.phone);
        member.setEmail(this.email);
        member.setAddress(this.address);
        member.setDetailAddress(this.detailAddress);
        member.setZipCode(this.zipCode);
        return member;
    }
}
