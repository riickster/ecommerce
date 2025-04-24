package com.itesm.ecommerce.infrastructure.mapper;
import com.itesm.ecommerce.domain.model.Cart;
import com.itesm.ecommerce.domain.model.CartHasProduct;
import com.itesm.ecommerce.infrastructure.entity.CartHasProductsEntity;

public class CartHasProductMapper {
    public static CartHasProduct toDomain(CartHasProductsEntity cartHasProductsEntity, Cart cart){
        CartHasProduct cartHasProduct = new CartHasProduct();
        cartHasProduct.setCart(cart);
        cartHasProduct.setProduct(ProductMapper.toDomain(cartHasProductsEntity.getProduct()));
        cartHasProduct.setQuantity(cartHasProductsEntity.getQuantity());
        return cartHasProduct;
    }

    public static CartHasProductsEntity toEntity(CartHasProduct cartHasProduct){
        CartHasProductsEntity cartHasProductsEntity = new CartHasProductsEntity();
        cartHasProductsEntity.setProduct(ProductMapper.toEntity(cartHasProduct.getProduct()));
        cartHasProductsEntity.setQuantity(cartHasProduct.getQuantity());
        return cartHasProductsEntity;
    }
}

