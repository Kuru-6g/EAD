package com.ead.cartservice.model;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Document("carts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {
    @Id
    private String id;
    @Indexed(unique = true)
    private UUID customerId;
    private List<CartItem> cartItems;
    private BigDecimal totalPrice;
}