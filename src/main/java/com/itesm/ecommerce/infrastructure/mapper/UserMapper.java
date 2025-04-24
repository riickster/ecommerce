package com.itesm.ecommerce.infrastructure.mapper;

import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.infrastructure.entity.UserEntity;

public class UserMapper {
    public static User toDomain(UserEntity userEntity){
        User user = new User();
        user.setId(userEntity.getId());
        user.setUuid(userEntity.getUuid());
        user.setEmail(userEntity.getEmail());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setFirebaseId(userEntity.getFirebaseId());
        return user;
    }

    public static UserEntity toEntity(User user){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setUuid(user.getUuid());
        userEntity.setEmail(user.getEmail());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setFirebaseId(user.getFirebaseId());
        return userEntity;
    }
}
