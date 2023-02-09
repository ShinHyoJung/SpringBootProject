package com.project.shop.feature.auth.service.impl;

import com.project.shop.feature.auth.dao.AuthDAO;
import com.project.shop.feature.auth.entity.Auth;
import com.project.shop.feature.auth.service.AuthService;
import com.project.shop.feature.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service("AuthService")
@RequiredArgsConstructor
public class DefaultAuthService implements AuthService {

    private final AuthDAO authDAO;

    @Override
    public void insert(Member member) {
        authDAO.insert(member);
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities(String username) {
         return authDAO.select(username);
    }

    @Override
    public void update(String username, String auth) {
        authDAO.update(username, auth);
    }

    @Override
    public void delete(String username) {
        authDAO.delete(username);
    }

    @Override
    public String getRole() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Collection<? extends GrantedAuthority> auth = ((UserDetails)principal).getAuthorities();
        String role = String.valueOf(auth.iterator().next());

        return role;
    }
}
