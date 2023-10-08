package com.example.AOManager.dto;

import com.example.AOManager.entity.UsersEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private long birthday;
    private String gender;
    private String address;
    private String phone;
    private boolean status;

    public UsersDto(UsersEntity usersEntity) {
        this.id = usersEntity.getId().toString();
        this.email = usersEntity.getEmail();
        this.firstName = usersEntity.getFirstName();
        this.lastName = usersEntity.getLastName();
        this.birthday = usersEntity.getBirthday();
        this.gender = usersEntity.getGender();
        this.address = usersEntity.getAddress();
        this.phone = usersEntity.getPhone();
        this.status =usersEntity.getStatus();
    }
}
