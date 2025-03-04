package com.zion.api.domain.User;

public class LoginResponseDTO {
    private String token;

    public LoginResponseDTO(String token) {
        this.token = token;
    }

    // Getter
    public String getToken() {
        return token;
    }
}