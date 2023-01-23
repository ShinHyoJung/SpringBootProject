package com.project.shop.feature.member.service.impl;

import com.project.shop.feature.login.dto.PostLogin;
import com.project.shop.feature.member.dao.MemberDAO;
import com.project.shop.feature.member.dto.GetInfoResponse;
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
    public Member selectByLoginID(String loginID) {
        return memberDAO.selectByLoginID(loginID);
    }

    @Override
    public Member selectByIdx(int idx) {
        return memberDAO.select(idx);
    }

    @Override
    public Member select(int idx) {
        return memberDAO.select(idx);
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
    public Member validateMember(String name, String birth) {
        return memberDAO.selectByNameAndBirth(name, birth);
    }

    @Override
    public boolean isDuplicateLoginID(String loginID) {
        boolean isDuplicate;
        try {
            memberDAO.isValidateLoginID(loginID);
            isDuplicate = true;
        } catch (Exception e) {
            isDuplicate = false;
        }
        return isDuplicate;
    }

    @Override
    public boolean isExistLoginID(String loginID) {
        boolean isExist;
        try {
            memberDAO.isValidateLoginID(loginID);
            isExist = true;
        } catch (Exception e) {
            isExist = false;
        }
        return isExist;
    }

    @Override
    public void updatePassword(String password, int idx) {
        memberDAO.updatePassword(password, idx);
    }
}
