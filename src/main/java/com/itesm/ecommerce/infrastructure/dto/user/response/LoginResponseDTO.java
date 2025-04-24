package com.itesm.ecommerce.infrastructure.dto.user.response;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String accessToken;
    private String refreshToken;
    private String expiresIn;
}
