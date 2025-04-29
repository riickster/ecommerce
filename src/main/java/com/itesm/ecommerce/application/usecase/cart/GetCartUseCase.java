package com.itesm.ecommerce.application.usecase.cart;

import com.itesm.ecommerce.application.service.CartService;
import com.itesm.ecommerce.domain.model.Cart;
import com.itesm.ecommerce.infrastructure.dto.cart.DefaultCartResponseDTO;
import com.itesm.ecommerce.infrastructure.mapper.CartMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetCartUseCase {

    @Inject CartService cartService;

    public DefaultCartResponseDTO execute(String firebaseId){
        return CartMapper.toDTO(cartService.getCart(firebaseId));
    }
}
