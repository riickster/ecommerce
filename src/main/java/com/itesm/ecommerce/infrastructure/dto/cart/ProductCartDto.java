package com.itesm.ecommerce.infrastructure.dto.cart;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCartDto {

    private int idProduct;
    private int quantity;
}
