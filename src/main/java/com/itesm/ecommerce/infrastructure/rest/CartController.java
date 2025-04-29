package com.itesm.ecommerce.infrastructure.rest;

import com.itesm.ecommerce.application.service.CartService;
import com.itesm.ecommerce.application.usecase.cart.*;
import com.itesm.ecommerce.infrastructure.dto.cart.ProductCartDto;
import com.itesm.ecommerce.lib.UserContext;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

@Path("/cart")
public class CartController {

    @Inject AddProductToCartUseCase addProductToCartUseCase;
    @Inject GetCartUseCase getCartUseCase;
    @Inject RemoveProductFromCartUseCase removeProductFromCartUseCase;
    @Inject CartService cartService;
    @Inject CheckoutCartUseCase checkoutCartUseCase;
    @Inject UpdateProductInCartUseCase updateProductInCartUseCase;
    @Inject ClearCartUseCase clearCartUseCase;

    @Inject UserContext userContext;

    @POST
    @Path("/add")
    public Response addProductToCart(ProductCartDto productCartDto) {
        addProductToCartUseCase.execute(productCartDto, userContext.getFirebaseId());
        Map<String,String> response= new HashMap<>();
        response.put("message", "Product added to cart");
        response.put("productId", String.valueOf(productCartDto.getIdProduct()));
        response.put("quantity", String.valueOf(productCartDto.getQuantity()));
        return Response.ok().entity(response).build();
    }

    @GET
    public Response getCart() {
//        CartSummaryDTO summary = new CartSummaryDTO();
//        summary.setSubtotal(cartService.calcuateSubtotal(cart));
//        summary.setTotal(cartService.calculateTotal(cart));
//        summary.setProducts(cart.getProductsInCart());
        return Response.ok().entity(getCartUseCase.execute(userContext.getFirebaseId())).build();
    }

    @DELETE
    @Path("/remove/{productId}/{quantity}")
    public Response removeProductFromCart(@PathParam("productId") int productId, @PathParam("quantity") int quantity) {
        removeProductFromCartUseCase.execute("YjfjbcfdlXQEdoxSTLjGWwuCKLD3", productId, quantity);
        Map<String,String> response = new HashMap<>();
        response.put("message", "Product removed from cart");
        response.put("productId", String.valueOf(productId));
        response.put("quantity", String.valueOf(quantity));
        return Response.ok().entity(response).build();
    }

    @PUT
    @Path("/update")
    public Response updateProductInCart(ProductCartDto dto) {
        updateProductInCartUseCase.execute(dto, "YjfjbcfdlXQEdoxSTLjGWwuCKLD3");
        Map<String, String> response = new HashMap<>();
        response.put("message", "Product quantity updated");
        response.put("productId", String.valueOf(dto.getIdProduct()));
        response.put("quantity", String.valueOf(dto.getQuantity()));
        return Response.ok().entity(response).build();
    }

    @DELETE
    @Path("/clear")
    public Response clearCart() {
        clearCartUseCase.execute("YjfjbcfdlXQEdoxSTLjGWwuCKLD3");
        Map<String,String> response = new HashMap<>();
        response.put("message", "Cart cleared successfully");
        return Response.ok().entity(response).build();
    }

    @POST
    @Path("/checkout")
    public Response checkoutCart() {
        checkoutCartUseCase.execute("YjfjbcfdlXQEdoxSTLjGWwuCKLD3");
        Map<String, String> response = new HashMap<>();
        response.put("message", "Cart paid successfully");
        return Response.ok().entity(response).build();
    }
}
