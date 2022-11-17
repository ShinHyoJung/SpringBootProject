package com.project.shop.feature.member.service.impl;

import com.project.shop.feature.member.dao.mapper.MemberMapper;
import com.project.shop.feature.member.dto.PostLogin;
import com.project.shop.feature.member.entity.Member;
import com.project.shop.feature.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

        Member member = memberMapper.select(postLogin.getId());
        if(member == null) {
            isValidate = false;
        }
        String password = member.getPassword();

        isValidate = bCryptPasswordEncoder.matches(postLogin.getPassword(), password);
        return isValidate;

    }
}
