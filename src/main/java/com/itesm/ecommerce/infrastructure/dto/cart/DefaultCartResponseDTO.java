package com.itesm.ecommerce.infrastructure.dto.cart;

import com.itesm.ecommerce.domain.model.CartHasProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DefaultCartResponseDTO {
    private String uuid;
    private String status;
    private List<CartHasProduct> products;
    private float subtotal;
    private float total;
}
