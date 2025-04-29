package com.itesm.ecommerce.domain.repository;

import com.itesm.ecommerce.domain.model.Cart;

public interface CartRepository {
    Cart createCart(String firebaseId, String cartUuid);
    void changeStatus(int cartId, String status);
    void emptyCart(int cartId);
    Cart findUserCart(String firebaseId);
}
