package com.itesm.ecommerce.infrastructure.dto.user.response;

import lombok.Data;

@Data
public class RegisterUserResponseDTO {
    private String uuid;
    private String firebaseId;
    private String accessToken;
    private String refreshToken;
    private String expiresIn;
}
