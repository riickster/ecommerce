package com.itesm.ecommerce.infrastructure.mapper;

import com.itesm.ecommerce.domain.model.Cart;
import com.itesm.ecommerce.domain.model.CartHasProduct;
import com.itesm.ecommerce.infrastructure.entity.CartEntity;

import java.util.ArrayList;

public class CartMapper {
    public static Cart toDomain(CartEntity cartEntity){
        Cart cart = new Cart();
        cart.setId(cartEntity.getId());
        cart.setStatus(cartEntity.getStatus());
        cart.setUser(UserMapper.toDomain(cartEntity.getUser()));
        cart.setProductsInCart(new ArrayList<>());
        for (int i = 0; i < cartEntity.getCartHasProductsEntity().size(); i++) {
            CartHasProduct cartHasProduct = CartHasProductMapper.toDomain(
                    cartEntity.getCartHasProductsEntity().get(i), cart
            ); // entidad -> dominio
            cart.getProductsInCart().add(cartHasProduct); // agregar al modelo de dominio
        }

        return cart;
    }

    public static CartEntity toEntity(Cart cart){
        CartEntity cartEntity = new CartEntity();
        cartEntity.setId(cart.getId());
        cartEntity.setStatus(cart.getStatus());
        return cartEntity;
    }
}
