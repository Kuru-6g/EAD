package org.example.cartservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Indexed;
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

    private UUID customerId;
    private List<CartItem> cartItems;
    private BigDecimal totalPrice;
}
