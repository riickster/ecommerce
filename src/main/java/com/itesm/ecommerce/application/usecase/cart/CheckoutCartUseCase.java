package com.itesm.ecommerce.application.usecase.cart;

import com.itesm.ecommerce.application.service.CartService;
import com.itesm.ecommerce.domain.model.Cart;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CheckoutCartUseCase {
    @Inject
    CartService cartService;

    @Transactional
    public void execute(String firebaseId) {
        cartService.chekoutCart(firebaseId);
    }
}

