package com.example.AOManager.controller;

import com.example.AOManager.payload.request.ChangePasswordRequest;
import com.example.AOManager.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/account")
public class TaiKhoanController {

    @Autowired
    TaiKhoanService taiKhoanService;

    @PutMapping("/forgetpassword/{email}")
    String taoMatKhauMoi (@PathVariable String email) {return this.taiKhoanService.taoMatKhauMoi(email);}

    @PutMapping("/changepassword")
    String doiMatKhau(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {return this.taiKhoanService.doiMatKhau(changePasswordRequest);}
}
