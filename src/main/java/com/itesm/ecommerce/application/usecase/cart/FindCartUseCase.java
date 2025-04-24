package com.itesm.ecommerce.application.usecase.cart;

import com.itesm.ecommerce.application.service.CartService;
import com.itesm.ecommerce.domain.model.Cart;
import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.domain.repository.CartRepository;
import com.itesm.ecommerce.domain.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FindCartUseCase {

    @Inject
    CartService cartService;

    public Cart execute(String firebaseId){

        //return cartService.findByUserId(firebaseId);
        return null;
    }
}
