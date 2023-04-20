package com.rudkids.rudkids.domain.cart;

import com.rudkids.rudkids.domain.cart.domain.Cart;
import com.rudkids.rudkids.domain.cart.domain.CartItem;
import com.rudkids.rudkids.domain.item.domain.Item;

import java.util.UUID;

public interface CartItemReader {

    CartItem getCartItem(Cart cart, Item item, CartCommand.AddCartItem command);
    CartItem getCartItem(UUID cartItemId);
}