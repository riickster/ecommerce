package com.itesm.ecommerce.application.usecase.user;

import com.itesm.ecommerce.application.service.AuthService;
import com.itesm.ecommerce.infrastructure.dto.user.request.LoginRequestDTO;
import com.itesm.ecommerce.infrastructure.dto.user.request.RegisterUserRequestDTO;
import com.itesm.ecommerce.infrastructure.dto.user.response.LoginResponseDTO;
import com.itesm.ecommerce.infrastructure.dto.user.response.RegisterUserResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RegisterUserUseCase {

    @Inject AuthService authService;
    @Inject LoginUseCase loginUseCase;

    public RegisterUserResponseDTO execute(RegisterUserRequestDTO dto) {
        RegisterUserResponseDTO registerUserResponseDTO = authService.registerUser(dto);
        LoginResponseDTO loginResponseDTO = loginUseCase.execute(new LoginRequestDTO(dto.getEmail(), dto.getPassword()));
        registerUserResponseDTO.setAccessToken(loginResponseDTO.getAccessToken());
        registerUserResponseDTO.setRefreshToken(loginResponseDTO.getRefreshToken());
        registerUserResponseDTO.setExpiresIn(loginResponseDTO.getExpiresIn());
        return registerUserResponseDTO;
    }
}
