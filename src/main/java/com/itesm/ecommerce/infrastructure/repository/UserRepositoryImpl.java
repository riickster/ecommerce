package com.itesm.ecommerce.infrastructure.repository;

import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.domain.repository.UserRepository;
import com.itesm.ecommerce.infrastructure.entity.UserEntity;
import com.itesm.ecommerce.infrastructure.mapper.UserMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository, PanacheRepositoryBase<UserEntity,Integer> {

    @Override
    public User getUserById(int userId) {
        UserEntity user = findById(userId);
        if (user == null) {
            return null;
        }
        return UserMapper.toDomain(user);
    }

    @Override
    public User findByFirebaseId(String firebaseId) {
        UserEntity user = find("firebaseId", firebaseId).firstResult();
        if (user == null) {
            return null;
        }
        return UserMapper.toDomain(user);
    }

    public UserEntity findEntityByFirebaseId(String firebaseId){
        return find("firebaseId", firebaseId).firstResult();
    }

    @Override
    public User registerUser(User user) {
        UserEntity userEntity = UserMapper.toEntity(user);
        userEntity.persist();
        return UserMapper.toDomain(userEntity);
    }
}
