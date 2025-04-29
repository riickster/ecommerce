package com.itesm.ecommerce.infrastructure.mapper;

import com.itesm.ecommerce.domain.model.Cart;
import com.itesm.ecommerce.domain.model.CartHasProduct;
import com.itesm.ecommerce.infrastructure.dto.cart.DefaultCartResponseDTO;
import com.itesm.ecommerce.infrastructure.entity.CartEntity;

import java.util.ArrayList;

public class CartMapper {
    public static Cart toDomain(CartEntity cartEntity){
        Cart cart = new Cart();
        cart.setId(cartEntity.getId());
        cart.setUuid(cartEntity.getUuid());
        cart.setStatus(cartEntity.getStatus());
        cart.setUser(UserMapper.toDomain(cartEntity.getUser()));
        cart.setProductsInCart(new ArrayList<>());
        if(cartEntity.getCartHasProductsEntity() != null){
            for (int i = 0; i < cartEntity.getCartHasProductsEntity().size(); i++) {
                CartHasProduct cartHasProduct = CartHasProductMapper.toDomain(
                        cartEntity.getCartHasProductsEntity().get(i), cart
                );
                cart.getProductsInCart().add(cartHasProduct);
            }
        }

        return cart;
    }

    public static CartEntity toEntity(Cart cart){
        CartEntity cartEntity = new CartEntity();
        cartEntity.setId(cart.getId());
        cartEntity.setStatus(cart.getStatus());
        cartEntity.setUser(UserMapper.toEntity(cart.getUser()));
        return cartEntity;
    }

    public static DefaultCartResponseDTO toDTO(Cart cart){
        DefaultCartResponseDTO dto = new DefaultCartResponseDTO();
        dto.setUuid(cart.getUuid());
        dto.setStatus(cart.getStatus());
        if(cart.getUser() != null){
            dto.setProducts(cart.getProductsInCart());
        }
        return dto;
    }

    public static DefaultCartResponseDTO toDTO(CartEntity cartEntity){
        return new DefaultCartResponseDTO();
    }
}
