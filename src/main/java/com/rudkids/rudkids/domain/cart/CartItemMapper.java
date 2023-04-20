package com.rudkids.rudkids.domain.cart;

import com.rudkids.rudkids.domain.cart.domain.CartItem;
import com.rudkids.rudkids.domain.cart.domain.CartItemOptionGroup;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartItemMapper {

    public CartItemInfo.Main toInfo(CartItem cartItem) {
        return CartItemInfo.Main.builder()
                .id(cartItem.getId())
                .name(cartItem.getName())
                .price(cartItem.getPrice())
                .optionGroups(toInfo(cartItem.getCartItemOptionGroups()))
                .itemStatus(cartItem.getItemStatus())
                .build();
    }

    private List<CartItemInfo.CartItemOptionGroup> toInfo(List<CartItemOptionGroup> groups) {
        return groups.stream()
                .map(this::toInfo)
                .toList();
    }

    private CartItemInfo.CartItemOptionGroup toInfo(CartItemOptionGroup cartItemOptionGroup) {
        return CartItemInfo.CartItemOptionGroup.builder()
                .name(cartItemOptionGroup.getName())
                .optionName(cartItemOptionGroup.getCartItemOption())
                .build();
    }
}
