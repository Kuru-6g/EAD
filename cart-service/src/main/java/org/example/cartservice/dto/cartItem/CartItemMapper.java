package org.example.cartservice.dto.cartItem;




import lombok.RequiredArgsConstructor;
import org.example.cartservice.model.CartItem;
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
