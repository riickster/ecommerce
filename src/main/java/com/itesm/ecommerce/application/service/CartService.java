package com.itesm.ecommerce.application.service;

import com.itesm.ecommerce.domain.model.Cart;
import com.itesm.ecommerce.domain.model.CartHasProduct;
import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.domain.repository.CartHasProductRepository;
import com.itesm.ecommerce.domain.repository.UserRepository;
import com.itesm.ecommerce.infrastructure.dto.cart.ProductCartDto;
import com.itesm.ecommerce.domain.repository.CartRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CartService {

    @Inject UserRepository userRepository;
    @Inject CartRepository cartRepository;
    @Inject CartHasProductRepository cartHasProductRepository;

    public Cart findByUserId(User user){
        return cartRepository.findByUserId(user.getId());
    }

    @Transactional
    public void addProductToCart(ProductCartDto dto, String firebaseId) {

        User user = userRepository.getUserByFirebaseId(firebaseId);
        Cart cart = cartRepository.findByUserId(user.getId());
        if(cart == null){
            cartRepository.createCart(user.getFirebaseId());
            cart = cartRepository.findByUserId(user.getId());
        }
        cartHasProductRepository.addProductToCart(cart.getId(),dto.getIdProduct(), dto.getQuantity());

    }

    @Transactional
    public void removeProductFromCart(String firebaseId, int productId, int quantity) {
        User user = userRepository.getUserByFirebaseId(firebaseId);
        Cart cart = cartRepository.findByUserId(user.getId());

        if (cart != null) {
            cartHasProductRepository.removeProductFromCart(cart.getId(), productId, quantity);
        }
    }

    @Transactional
    public void updateProductInCart(ProductCartDto dto, String firebaseId) {
        User user = userRepository.getUserByFirebaseId(firebaseId);
        Cart cart = cartRepository.findByUserId(user.getId());

        if (cart != null) {
            cartHasProductRepository.updateProductQuantity(cart.getId(), dto.getIdProduct(), dto.getQuantity());
        }
    }

    public float calcuateSubtotal(Cart cart){
        float subtotal = 0;
        for(int i = 0; i < cart.getProductsInCart().size(); i++){
            CartHasProduct item = cart.getProductsInCart().get(i);
            subtotal += item.getProduct().getPrice() * item.getQuantity();
        }
        return subtotal;
    }

    public float calculateTotal(Cart cart){
        float subtotal = calcuateSubtotal(cart);
        float taxes = 0.16f; // mexico iVA 16%
        float total = subtotal + (subtotal * taxes);
        return total;
    }

    @Transactional
    public void chekoutCart(String firebaseId) {
        User user = userRepository.getUserByFirebaseId(firebaseId);
        Cart cart = cartRepository.findByUserId(user.getId());

        if (cart != null) {
            cartRepository.changeStatus(cart.getId(), "paid");
        }
    }

    public void clearCart(Cart cart) {
        cartHasProductRepository.clearCart(cart.getId());
    }
}
