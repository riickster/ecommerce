package com.itesm.ecommerce.infrastructure.mapper;

import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.infrastructure.entity.UserEntity;

public class UserMapper {
    public static User toDomain(UserEntity userEntity){
        User user = new User();
        user.setId(userEntity.getId());
        user.setUuid(userEntity.getUuid());
        user.setEmail(userEntity.getEmail());
        user.setFirebaseId(userEntity.getFirebaseId());
        return user;
    }

    public static UserEntity toEntity(User user){
        UserEntity userEntity = new UserEntity();
        userEntity.setUuid(user.getUuid());
        userEntity.setEmail(user.getEmail());
        userEntity.setFirebaseId(user.getFirebaseId());
        return userEntity;
    }
}
