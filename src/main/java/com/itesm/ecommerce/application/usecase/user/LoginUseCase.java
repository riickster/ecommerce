package com.itesm.ecommerce.application.usecase.user;

import com.itesm.ecommerce.application.service.AuthService;
import com.itesm.ecommerce.infrastructure.dto.user.request.LoginRequestDTO;
import com.itesm.ecommerce.infrastructure.dto.user.response.LoginResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class LoginUseCase {

    @Inject AuthService authService;

    public LoginResponseDTO execute(LoginRequestDTO requestDTO) {
        return authService.login(requestDTO);
    }
}
