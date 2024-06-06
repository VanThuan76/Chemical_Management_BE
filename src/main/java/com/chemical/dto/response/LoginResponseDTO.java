package com.chemical.dto.response;

import com.chemical.entity.User;
import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;
    private UserResponseDTO user;

    public LoginResponseDTO(String token, UserResponseDTO user) {
        this.token = token;
        this.user = user;
    }
}

