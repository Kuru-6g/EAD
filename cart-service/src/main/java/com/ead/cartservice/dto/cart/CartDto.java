package com.ead.cartservice.dto.cart;

import com.ead.cartservice.dto.cartItem.CartItemDto;
import com.ead.cartservice.model.CartItem;

import lombok.Builder;
import lombok.Data;

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
