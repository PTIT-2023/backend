package com.example.AOManager.service.impl;

import com.example.AOManager.email.EmailDetail;
import com.example.AOManager.email.EmailService;
import com.example.AOManager.entity.EmployeeEntity;
import com.example.AOManager.payload.request.ChangePasswordRequest;
import com.example.AOManager.repository.EmployeeRepository;
import com.example.AOManager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    EmailService emailService;

    public String createRandomPassword() {
        Random generator = new Random();
        int value = generator.nextInt((999999 - 100000) + 1) + 100000;
        return String.valueOf(value); //tạo ra một chuỗi gồm 6 chữ số ngẫu nhiên
    }

    @Override
    public int resetPassword(String email) {
        if(this.employeeRepository.existsByEmail(email)) {
            EmployeeEntity employee = this.employeeRepository.findByEmail(email).get();
            String newPassword = this.createRandomPassword();
            employee.setPassword(encoder.encode(newPassword));
            try {
                this.employeeRepository.save(employee);
            } catch (Exception e) {
                return 2; // thất bại
            }
            EmailDetail emailDetail = new EmailDetail();
            emailDetail.setSubject("ĐẶT LẠI MẬT KHẨU");
            emailDetail.setMsgBody("Mật khẩu mới của bạn là: " + newPassword);
            emailDetail.setRecipient(email);
            try {
                this.emailService.sendSimpleMail(emailDetail);
            }
            catch (Exception e) {
                return 2;
            }
        } else return 3; // không tìm thấy
        return 1; // thành công
    }

    @Override
    public int changePassword(ChangePasswordRequest changePasswordRequest) {
        EmployeeEntity employee = this.employeeRepository.findById(UUID.fromString(changePasswordRequest.getId())).get();
        if(!encoder.matches(changePasswordRequest.getOldPassword(), employee.getPassword())) {
            return 3; // mật khẩu cũ không khớp
        }
        if(!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getNewPasswordConfirm())){
            return 4; // Mật khẩu mới xác nhận lại không khớp
        }
        employee.setPassword(encoder.encode(changePasswordRequest.getNewPassword()));
        try {
            this.employeeRepository.save(employee);
            return 1; // thành công
        }
        catch (Exception e) {
            return 2; // thất bại
        }
    }
}
