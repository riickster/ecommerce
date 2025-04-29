package com.itesm.ecommerce.infrastructure.dto.user.request;

import com.itesm.ecommerce.domain.model.User;
import lombok.Data;

@Data
public class RegisterUserRequestDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public User toDomain(){
        User user = new User();
        user.setEmail(email);
        return user;
    }
}
