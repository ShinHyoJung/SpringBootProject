package com.project.shop.feature.member.service.impl;

import com.project.shop.feature.auth.dao.AuthDAO;
import com.project.shop.feature.auth.service.AuthService;
import com.project.shop.feature.login.dto.PostLogin;
import com.project.shop.feature.member.dao.MemberDAO;
import com.project.shop.feature.member.dto.GetInfoResponse;
import com.project.shop.feature.member.entity.Member;
import com.project.shop.feature.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Date;
import java.util.Enumeration;

@Service
@RequiredArgsConstructor
public class DefaultMemberService implements MemberService {

    private final MemberDAO memberDAO;
    private final AuthService authService;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberDAO.selectByLoginID(username);
        member.setAuthorities(authService.getAuthorities(username));
        return member;
    }
}
