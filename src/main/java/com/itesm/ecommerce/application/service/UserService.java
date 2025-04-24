package com.itesm.ecommerce.application.service;

import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.domain.repository.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserService {

    @Inject UserRepository userRepository;

    public User getUser(String firebaseId){
        return userRepository.getUserByFirebaseId(firebaseId);
    }
}
