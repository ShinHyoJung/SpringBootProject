package com.project.shop.feature.member.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Data
public class Member implements UserDetails {
    private int idx;
    private String loginID;
    private String password;
    private String name;
    private String birth;
    private String phone;
    private String email;
    private String address;
    private String detailAddress;
    private String zipCode;
    private Date createDate;
    private Date updateDate;
    private Collection<GrantedAuthority> authorities;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialNonExpired;
    private boolean isEnabled;

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.loginID;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
