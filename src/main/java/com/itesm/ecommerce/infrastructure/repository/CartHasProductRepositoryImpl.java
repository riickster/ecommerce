package com.itesm.ecommerce.infrastructure.repository;

import com.itesm.ecommerce.domain.repository.CartHasProductRepository;
import com.itesm.ecommerce.infrastructure.entity.CartHasProductsEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CartHasProductRepositoryImpl implements CartHasProductRepository, PanacheRepository<CartHasProductsEntity> {
    @Inject
    CartRepositoryImpl cartRepository;

    @Inject
    ProductRepositoryImpl productRepository;
    @Override
    public void addProductToCart(int cartId, int productId, int quantity) {
        System.out.println("Adding product with ID " + productId + " to cart with ID " + cartId + " with quantity " + quantity);
        CartHasProductsEntity cartHasProduct = new CartHasProductsEntity();
        cartHasProduct.setCart(cartRepository.getCartById(cartId));
        cartHasProduct.setProduct(productRepository.findById(productId));
        cartHasProduct.setQuantity(quantity);
        persist(cartHasProduct);
        System.out.println("Product added to cart successfully.");
    }

    @Override
    public void removeProductFromCart(int cartId, int productId, int quantity) {
        CartHasProductsEntity entity = find("cart.id = ?1 and product.id = ?2", cartId, productId).firstResult();
        if (entity != null) {
            int currentQuantity = entity.getQuantity();
            if (currentQuantity > quantity) {
                entity.setQuantity(currentQuantity - quantity);
                persist(entity); // actualiza la cantidad
                System.out.println("Reduced quantity of product in cart.");
            } else {
                delete(entity); // si la cantidad llega a 0 o menos, se quita del carrito
                System.out.println("Product removed completely from cart.");
            }
        } else {
            System.out.println("No such product in cart to remove.");
        }
    }

    public void updateProductQuantity(int cartId, int productId, int quantity) {
        CartHasProductsEntity item = find("cart.id = ?1 and product.id = ?2", cartId, productId).firstResult();
        if (item != null) {
            item.setQuantity(quantity);
            persist(item);
        }
    }

    public void clearCart(int cartId) {
        delete("cart.id", cartId); // elimina todas las filas con ese cart_id
    }

}
