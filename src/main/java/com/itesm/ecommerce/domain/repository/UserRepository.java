package com.itesm.ecommerce.domain.repository;

import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.infrastructure.entity.UserEntity;

public interface UserRepository {
    User getUserById(int userId);
    User findByFirebaseId(String firebaseId);
    UserEntity findEntityByFirebaseId(String firebaseId);
    User registerUser(User user);
}
