package com.rudkids.rudkids.interfaces.cart.dto;

import com.rudkids.rudkids.domain.cart.CartCommand;
import org.springframework.stereotype.Component;

@Component
public class CartDtoMapper {

    public CartCommand.AddCartItem to(CartRequest.AddCartItem request) {
        return CartCommand.AddCartItem.builder()
                .itemId(request.itemId())
                .amount(request.amount())
                .build();
    }

    public CartCommand.UpdateCartItemAmount to(CartRequest.UpdateCartItemAmount request) {
        return CartCommand.UpdateCartItemAmount.builder()
                .cartId(request.cartId())
                .cartItemId(request.cartItemId())
                .amount(request.amount())
                .build();
    }

    public CartCommand.DeleteCartItems to(CartRequest.DeleteCartItems request) {
        return CartCommand.DeleteCartItems.builder()
                .cartId(request.cartId())
                .cartItemIds(request.cartItemIds())
                .build();
    }
}