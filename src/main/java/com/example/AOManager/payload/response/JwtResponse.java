package com.example.AOManager.payload.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    private String id;
    private List<String> roles;

    public JwtResponse(String accessToken, String id, String username, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.roles = roles;
    }

}
