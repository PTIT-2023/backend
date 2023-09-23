package com.example.AOManager.security.services;

import com.example.AOManager.entity.TaiKhoanEntity;
import com.example.AOManager.repository.TaiKhoanRepository;
import com.example.AOManager.entity.TaiKhoanEntity;
import com.example.AOManager.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    TaiKhoanRepository taiKhoanRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String maTK) throws UsernameNotFoundException {
        TaiKhoanEntity taiKhoan = taiKhoanRepository.findByMaTK(maTK)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with user name" + maTK));
        return UserDetailsImpl.build(taiKhoan);
    }
}
