package com.project.shop.feature.auth.service.impl;

import com.project.shop.feature.auth.dao.AuthDAO;
import com.project.shop.feature.auth.entity.Auth;
import com.project.shop.feature.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AuthService")
@RequiredArgsConstructor
public class DefaultAuthService implements AuthService {

    private final AuthDAO authDAO;

    @Override
    public void insert(Auth auth) {
        authDAO.insert(auth);
    }

    @Override
    public List<GrantedAuthority> select(String username) {
        return authDAO.selectList(username);
    }

    @Override
    public void update(String username, String auth) {
        authDAO.update(username, auth);
    }
}
