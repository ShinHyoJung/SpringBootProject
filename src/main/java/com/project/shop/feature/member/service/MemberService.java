package com.project.shop.feature.member.service;

import com.project.shop.feature.member.dto.GetInfoResponse;
import com.project.shop.feature.login.dto.PostLogin;
import com.project.shop.feature.member.entity.Member;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {

    void insert(Member member);

    Member selectByLoginID(String loginID);

    Member selectByIdx(int idx);

    Member select(int idx);

    void update(Member member);

    void delete(int idx);

    Member validateMember(String name, String birth);

    boolean isDuplicateLoginID(String loginID);

    boolean isExistLoginID(String loginID);

    void updatePassword(String password, int idx);

    UserDetails loadUserByUsername(String username);

    int selectIdxByUsername();
}
