package com.itesm.ecommerce.application.usecase.user;

import com.itesm.ecommerce.application.service.UserService;
import com.itesm.ecommerce.domain.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetUserByFirebaseIdUseCase {

    @Inject UserService userService;

    public User execute(String firebaseId){
        return userService.getUser(firebaseId);
    }
}
