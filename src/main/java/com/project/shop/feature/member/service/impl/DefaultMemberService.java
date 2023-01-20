package com.project.shop.feature.member.service.impl;

import com.project.shop.feature.member.dao.MemberDAO;
import com.project.shop.feature.member.dto.GetInfoResponse;
import com.project.shop.feature.member.dto.PostLogin;
import com.project.shop.feature.member.entity.Member;
import com.project.shop.feature.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class DefaultMemberService implements MemberService {

    private final MemberDAO memberDAO;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public void insert(Member member) {
        memberDAO.insert(member);
    }

    @Override
    public boolean isValidateIDPWD(PostLogin postLogin) {
        boolean isValidate;

        Member member = memberDAO.selectByLoginID(postLogin.getId());
        if(member == null) {
            isValidate = false;
        } else {
            String password = member.getPassword();
            isValidate = bCryptPasswordEncoder.matches(postLogin.getPassword(), password);
        }
        return isValidate;
    }

    @Override
    public Member selectByLoginID(String loginID) {
        return memberDAO.selectByLoginID(loginID);
    }

    @Override
    public Member selectByIdx(int idx) {
        return memberDAO.select(idx);
    }

    @Override
    public GetInfoResponse selectInfo(Member member) {
        GetInfoResponse getInfoResponse = new GetInfoResponse(member.getIdx(), member.getLoginID(), member.getName());

        if(StringUtils.isNotEmpty(member.getBirth())) {
            String birth = member.getBirth();
            getInfoResponse.setBirth(birth);
        }

        if(StringUtils.isNotEmpty(member.getPhone())) {
            String phone = member.getPhone();
            getInfoResponse.setPhone(phone);
        }

        if(StringUtils.isNotEmpty(member.getEmail())) {
            String email = member.getEmail();
            getInfoResponse.setEmail(email);
        }

        if(StringUtils.isNotEmpty(member.getAddress())) {
            String address = member.getAddress();
            getInfoResponse.setAddress(address);
        }

        if(StringUtils.isNotEmpty(member.getDetailAddress())) {
            String detailAddress = member.getDetailAddress();
            getInfoResponse.setDetailAddress(detailAddress);
        }

        if(StringUtils.isNotEmpty(member.getZipCode())) {
            String zipCode = member.getZipCode();
            getInfoResponse.setZipCode(zipCode);
        }

        Date createDate = member.getCreateDate();
        Date updateDate = member.getUpdateDate();
        getInfoResponse.setCreateDate(createDate);
        getInfoResponse.setUpdateDate(updateDate);

        return getInfoResponse;
    }

    @Override
    public void update(Member member) {
        memberDAO.update(member);
    }

    @Override
    public void delete(int idx) {
        memberDAO.delete(idx);
    }

    @Override
    public Member selectByNameAndBirth(String name, String birth) {
        return memberDAO.selectByNameAndBirth(name, birth);
    }

    @Override
    public boolean validateLoginID(String loginID) {
        boolean isDuplicate;
        try {
            memberDAO.validateLoginID(loginID);
            isDuplicate = true;
        } catch (Exception e) {
            isDuplicate = false;
        }
        return isDuplicate;
    }
}
