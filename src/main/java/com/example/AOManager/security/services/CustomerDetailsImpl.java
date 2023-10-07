package com.example.AOManager.security.services;

import com.example.AOManager.entity.CustomerEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class CustomerDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    private String email;
    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public CustomerDetailsImpl(String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static CustomerDetailsImpl build(CustomerEntity customer) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        //GrantedAuthority t =   new SimpleGrantedAuthority(employee.getRoleId().getName().toString());
        authorities.add(null);

        return new CustomerDetailsImpl(customer.getEmail(), customer.getPassword(), authorities);
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
        CustomerDetailsImpl user = (CustomerDetailsImpl) obj;
        return Objects.equals(email, user.email);
    }
}
