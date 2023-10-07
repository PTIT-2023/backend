package com.example.AOManager.service.impl;

import com.example.AOManager.email.EmailDetail;
import com.example.AOManager.email.EmailService;
import com.example.AOManager.entity.CustomerEntity;
import com.example.AOManager.entity.EmployeeEntity;
import com.example.AOManager.payload.request.ChangePasswordRequest;
import com.example.AOManager.repository.CustomerRepository;
import com.example.AOManager.repository.EmployeeRepository;
import com.example.AOManager.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

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
    public String resetPassword(String email) {
        if(this.customerRepository.existsByEmail(email)) {
            CustomerEntity customer = this.customerRepository.findByEmail(email).get();
            String newPassword = this.createRandomPassword();
            customer.setPassword(encoder.encode(newPassword));
            try {
                this.customerRepository.save(customer);
            } catch (Exception e) {
                return "2"; // thất bại
            }
            EmailDetail emailDetail = new EmailDetail();
            emailDetail.setSubject("ĐẶT LẠI MẬT KHẨU");
            emailDetail.setMsgBody("Mật khẩu mới của bạn là: " + newPassword);
            emailDetail.setRecipient(email);
            try {
                this.emailService.sendSimpleMail(emailDetail);
            }
            catch (Exception e) {
                return "2";
            }
        } else return "3"; // không tìm thấy email
        return "1"; // thành công
    }

    @Override
    public int changePassword(ChangePasswordRequest changePasswordRequest) {
        CustomerEntity customer = this.customerRepository.findById(UUID.fromString(changePasswordRequest.getId())).get();
        if(!encoder.matches(changePasswordRequest.getOldPassword(), customer.getPassword())) {
            return 3; // mật khẩu cũ không khớp
        }
        if(!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getNewPasswordConfirm())){
            return 4; // Mật khẩu mới xác nhận lại không khớp
        }
        customer.setPassword(encoder.encode(changePasswordRequest.getNewPassword()));
        try {
            this.customerRepository.save(customer);
            return 1; // thành công
        }
        catch (Exception e) {
            return 2; // thất bại
        }
    }
}
