package com.itesm.ecommerce.infrastructure.repository;

import com.itesm.ecommerce.domain.model.Cart;

import com.itesm.ecommerce.domain.repository.CartRepository;
import com.itesm.ecommerce.infrastructure.entity.CartEntity;
import com.itesm.ecommerce.infrastructure.mapper.CartMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CartRepositoryImpl implements CartRepository, PanacheRepositoryBase<CartEntity, Integer> {

    @Inject
    UserRepositoryImpl userRepository;

    @Override
    public void createCart(String userId) {
        CartEntity cart = new CartEntity();
        cart.setUser(userRepository.getUserEntityByFirebaseId(userId));
        cart.setStatus("active");
        persist(cart);
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
    public Cart findByUserId(int id) {
        CartEntity cart = find("user.id", id).firstResult();
        if (cart == null) {
            return null;
        }
        return CartMapper.toDomain(cart);
    }

    public CartEntity getCartById(int cartId) {
        return findById(cartId);
    }

}
