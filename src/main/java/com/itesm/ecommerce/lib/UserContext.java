package com.itesm.ecommerce.lib;

import jakarta.enterprise.context.RequestScoped;
import lombok.Data;

@Data
@RequestScoped
public class UserContext {
    private String firebaseId;
}
