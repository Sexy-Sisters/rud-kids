package com.rudkids.rudkids.domain.cart.domain;

import com.github.f4b6a3.ulid.UlidCreator;
import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tbl_cart_item")
public class CartItem {
    @Id
    @Column(name = "cart_item_id", columnDefinition = "BINARY(16)")
    private final UUID id = UlidCreator.getMonotonicUlid().toUuid();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "amount")
    private int amount;

    protected CartItem() {
    }

    private CartItem(Cart cart, Item item, int amount) {
        this.cart = cart;
        this.item = item;
        this.amount = amount;
    }

    public static CartItem create(Cart cart, Item item, int amount) {
        return new CartItem(cart, item, amount);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return item.getName();
    }

    public int getPrice() {
        return item.getPrice();
    }

    public ItemStatus getItemStatus() {
        return item.getItemStatus();
    }

    public int getAmount() {
        return amount;
    }

    public int getCartItemPrice() {
        return item.getPrice() * amount;
    }

    public void updateAmount(int amount) {
        this.amount = amount;
    }
}
