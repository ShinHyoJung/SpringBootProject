package com.project.shop.feature.auth.service;

import com.project.shop.feature.auth.entity.Auth;
import com.project.shop.feature.member.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

public interface AuthService {

    void insert(Member member);

    Collection<GrantedAuthority> getAuthorities(String username);

    void update(String username, String auth);

    void delete(String username);

    String getRole();
}
