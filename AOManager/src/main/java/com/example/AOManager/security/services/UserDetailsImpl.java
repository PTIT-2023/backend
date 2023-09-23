package com.example.AOManager.security.services;

import com.example.AOManager.entity.TaiKhoanEntity;
import com.example.AOManager.entity.TaiKhoanEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    private String maTK;
    @JsonIgnore
    private String matKhau;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String maTK, String matKhau, Collection<? extends GrantedAuthority> authorities) {
        this.maTK = maTK;
        this.matKhau = matKhau;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(TaiKhoanEntity user) {

        Collection<GrantedAuthority> authorities = new ArrayList<>();

        GrantedAuthority t =   new SimpleGrantedAuthority(user.getMaQuyen().getTenQuyen().toString());
        authorities.add(t);

        return new UserDetailsImpl(user.getMaTK(), user.getMatKhau(), authorities);

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return matKhau;
    }

    @Override
    public String getUsername() {
        return maTK;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserDetailsImpl user = (UserDetailsImpl) obj;
        return Objects.equals(maTK, user.maTK);
    }
}
