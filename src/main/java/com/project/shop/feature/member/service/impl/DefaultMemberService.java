package com.project.shop.feature.member.service.impl;

import com.project.shop.feature.member.dao.mapper.MemberMapper;
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

    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public void insert(Member member) {
        memberMapper.insert(member);
    }

    @Override
    public boolean isValidateIDPWD(PostLogin postLogin) {
        boolean isValidate;

        Member member = memberMapper.selectID(postLogin.getId());
        if(member == null) {
            isValidate = false;
        } else {
            String password = member.getPassword();
            isValidate = bCryptPasswordEncoder.matches(postLogin.getPassword(), password);
        }
        return isValidate;
    }

    @Override
    public Member select(String memberID) {
        return memberMapper.selectID(memberID);
    }

    @Override
    public Member select(int idx) {
        return memberMapper.select(idx);
    }

    @Override
    public GetInfoResponse selectInfo(Member member) {
        GetInfoResponse getInfoResponse = new GetInfoResponse(member.getIdx(), member.getMemberID(), member.getName());

        if(StringUtils.isNotEmpty(member.getBirth())) {
            String birth = member.getBirth();
            getInfoResponse.setBirth(birth);
        }

        if(StringUtils.isNotEmpty(member.getMobile())) {
            String mobile = member.getMobile();
            getInfoResponse.setMobile(mobile);
        }

        if(StringUtils.isNotEmpty(member.getMail())) {
            String mail = member.getMail();
            getInfoResponse.setMail(mail);
        }

        if(StringUtils.isNotEmpty(member.getAddress())) {
            String address = member.getAddress();
            getInfoResponse.setAddress(address);
        }

        Date createDate = member.getCreateDate();
        Date updateDate = member.getUpdateDate();
        getInfoResponse.setCreateDate(createDate);
        getInfoResponse.setUpdateDate(updateDate);

        return getInfoResponse;
    }

    @Override
    public void update(Member member) {
        memberMapper.update(member);
    }

    @Override
    public void delete(int idx) {
        memberMapper.delete(idx);
    }
}
