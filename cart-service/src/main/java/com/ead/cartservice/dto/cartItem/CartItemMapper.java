package com.ead.cartservice.dto.cartItem;

import com.ead.cartservice.model.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CartItemMapper {
    public CartItem createCartItemRequestToCartItem(CreateCartItemRequest createCartItemRequest){
        return CartItem.builder()
                .name(createCartItemRequest.getName())
                .price(createCartItemRequest.getPrice())
                .productId(createCartItemRequest.getProductId())
                .quantity(createCartItemRequest.getQuantity())
                .build();
    }

}
