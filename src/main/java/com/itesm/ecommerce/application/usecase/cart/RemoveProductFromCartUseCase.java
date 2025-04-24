package com.itesm.ecommerce.application.usecase.cart;

import com.itesm.ecommerce.application.service.CartService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class RemoveProductFromCartUseCase {

    @Inject
    CartService cartService;

    @Transactional
    public void execute(String firebaseId, int productId, int quantity) {
        cartService.removeProductFromCart(firebaseId, productId, quantity);
    }
}

