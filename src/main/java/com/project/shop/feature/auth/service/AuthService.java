package com.project.shop.feature.auth.service;

import com.project.shop.feature.auth.entity.Auth;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface AuthService {

    void insert(Auth auth);

    List<GrantedAuthority> select(String username);

    void update(String username, String auth);
}
