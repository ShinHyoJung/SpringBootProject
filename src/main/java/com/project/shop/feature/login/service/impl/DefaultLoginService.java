package com.project.shop.feature.login.service.impl;

import com.project.shop.feature.login.dto.PostLogin;
import com.project.shop.feature.login.service.LoginService;
import com.project.shop.feature.member.dao.MemberDAO;
import com.project.shop.feature.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("LoginService")
@RequiredArgsConstructor
public class DefaultLoginService implements LoginService {

    private final MemberDAO memberDAO;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean isValidateIDPWD(String loginID, String password) {
        boolean isValidate;

        try {
            Member member = memberDAO.selectByLoginID(loginID);
            String standardPassword = member.getPassword();
            isValidate = bCryptPasswordEncoder.matches(password, standardPassword);
        } catch (Exception e) {
            isValidate = false;
        }
        return isValidate;
    }
}
