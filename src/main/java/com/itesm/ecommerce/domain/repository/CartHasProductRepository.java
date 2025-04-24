package com.itesm.ecommerce.domain.repository;

public interface CartHasProductRepository {

    public void addProductToCart(int cartId, int productId, int quantity);
    public void removeProductFromCart(int cartId, int productId, int quantity);
    public void updateProductQuantity(int cartId, int productId, int quantity);
    public void clearCart(int cartId);
}
