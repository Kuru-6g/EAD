package org.example.cartservice.dto.cart;



import lombok.Builder;
import lombok.Data;
import org.example.cartservice.dto.cartItem.CartItemDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class CartDto {
    private UUID customerId;
    private List<CartItemDto> cartItems;
    private BigDecimal totalPrice;
}
