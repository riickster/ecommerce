package com.itesm.ecommerce.infrastructure.repository;

import com.itesm.ecommerce.domain.model.Cart;

import com.itesm.ecommerce.domain.repository.CartRepository;
import com.itesm.ecommerce.domain.repository.UserRepository;
import com.itesm.ecommerce.infrastructure.entity.CartEntity;
import com.itesm.ecommerce.infrastructure.entity.UserEntity;
import com.itesm.ecommerce.infrastructure.mapper.CartMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CartRepositoryImpl implements CartRepository, PanacheRepositoryBase<CartEntity, Integer> {

    @Inject
    UserRepository userRepository;

    @Override
    public Cart createCart(String firebaseId, String cartUuid) {
        UserEntity userEntity = userRepository.findEntityByFirebaseId(firebaseId);

        CartEntity cartEntity = new CartEntity();
        cartEntity.setUuid(cartUuid);
        cartEntity.setUser(userEntity);
        cartEntity.setStatus("ACTIVE");
        cartEntity.persist();
        return CartMapper.toDomain(cartEntity);
    }

    @Override
    public void changeStatus(int cartId, String status) {
        CartEntity cart = findById(cartId);
        if (cart != null) {
            cart.setStatus(status);
            persist(cart);
        }
    }

    @Override
    public void emptyCart(int cartId) {
    }

    @Override
    public Cart findUserCart(String firebaseId) {
        CartEntity cart = find("user.firebaseId", firebaseId).firstResult();
        if (cart == null) {
            return null;
        }
        return CartMapper.toDomain(cart);
    }

    public CartEntity findCartById(int cartId) {
        return findById(cartId);
    }

}
