package com.itesm.ecommerce.application.usecase.cart;

import com.itesm.ecommerce.application.service.CartService;
import com.itesm.ecommerce.infrastructure.dto.cart.ProductCartDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UpdateProductInCartUseCase {

    @Inject
    CartService cartService;

    public void execute(ProductCartDto dto, String firebaseId) {
        cartService.updateProductInCart(dto, firebaseId);
    }
}

