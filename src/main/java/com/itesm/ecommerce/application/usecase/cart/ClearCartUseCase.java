package com.itesm.ecommerce.application.usecase.cart;

import com.itesm.ecommerce.domain.model.Cart;
import com.itesm.ecommerce.application.service.CartService;
import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.domain.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ClearCartUseCase {

    @Inject CartService cartService;
    @Inject UserRepository userRepository;

    @Transactional
    public void execute(String firebaseId) {
        User user = userRepository.getUserByFirebaseId(firebaseId);
        Cart cart = cartService.findByUserId(user);
        if (cart != null) {
            cartService.clearCart(cart);
        }
    }
}

