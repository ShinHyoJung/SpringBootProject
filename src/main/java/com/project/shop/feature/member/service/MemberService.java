package com.project.shop.feature.member.service;

import com.project.shop.feature.member.dto.GetInfoResponse;
import com.project.shop.feature.member.dto.PostLogin;
import com.project.shop.feature.member.entity.Member;

public interface MemberService {

    void insert(Member member);

    boolean isValidateIDPWD(PostLogin postLogin);

    Member selectByLoginID(String loginID);

    Member selectByIdx(int idx);

    GetInfoResponse selectInfo(Member member);

    void update(Member member);

    void delete(int idx);

    Member selectByNameAndBirth(String name, String birth);

    boolean validateLoginID(String loginID);
}
