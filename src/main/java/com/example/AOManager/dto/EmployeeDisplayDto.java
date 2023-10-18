package com.example.AOManager.dto;

import com.example.AOManager.entity.UsersEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDisplayDto {

    private String avatar;
    private String name;
    private String email;
    private String address;
    private String phone;

    public EmployeeDisplayDto(UsersEntity usersEntity) {
        this.setAvatar(usersEntity.getAvatar());
        this.setName(usersEntity.getLastName() + " " + usersEntity.getFirstName());
        this.setEmail(usersEntity.getEmail());
        this.setAddress(usersEntity.getAddress());
        this.setPhone(usersEntity.getPhone());
    }
}
