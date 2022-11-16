package com.project.shop.feature.member.dto;

import com.project.shop.feature.member.entity.Member;
import lombok.Data;

import java.util.Date;

@Data
public class PostSignUp {
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

    public Member toEntity() {
        Member member = new Member();
        member.setIdx(this.idx);
        member.setMemberID(this.memberID);
        member.setPassword(this.password);
        member.setName(this.name);
        member.setBirth(this.birth);
        member.setMobile(this.mobile);
        member.setMail(this.mail);
        member.setAddress(this.address);
        member.setCreateDate(this.createDate);
        member.setUpdateDate(this.updateDate);

        return member;
    }
}
