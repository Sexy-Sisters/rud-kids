package com.rudkids.core.cart.domain;

import com.rudkids.core.item.domain.*;
import com.rudkids.core.user.domain.PhoneNumber;
import com.rudkids.core.user.domain.ProfileImage;
import com.rudkids.core.user.domain.User;
import com.rudkids.core.user.domain.UserName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CartItemTest {

    @DisplayName("장바구니아이템 가격을 계산한다.")
    @Test
    void 장바구니아이템_가격을_계산한다() {
        //given
        User user = User.builder()
            .email("namse@gmail.com")
            .name(UserName.create("남세"))
            .profileImage(ProfileImage.create("path", "url"))
            .build();
        Cart cart = Cart.create(user);

        //when
        Item item = Item.builder()
                .name(Name.create("No.1", "남바완"))
                .price(Price.create(2_000))
                .quantity(Quantity.create(1_000))
                .itemBio(ItemBio.create("소개글입니다~"))
                .limitType(LimitType.LIMITED)
                .build();

        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .item(item)
                .amount(4)
                .price(item.getPrice())
                .build();

        //then
        assertThat(cartItem.calculateTotalItemPrice())
            .isEqualTo(8000);
    }

    @DisplayName("장바구니아이템 수량을 변경한다.")
    @Test
    void 장바구니아이템_수량을_변경한다() {
        //given
        User user = User.builder()
            .email("namse@gmail.com")
            .name(UserName.create("남세"))
            .profileImage(ProfileImage.create("path", "url"))
            .build();
        Cart cart = Cart.create(user);

        Item item = Item.builder()
                .name(Name.create("No.1", "남바완"))
                .price(Price.create(2_000))
                .quantity(Quantity.create(1_000))
                .itemBio(ItemBio.create("소개글입니다~"))
                .limitType(LimitType.LIMITED)
                .build();

        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .item(item)
                .amount(2)
                .price(item.getPrice())
                .build();

        //when
        cartItem.updateAmount(4);

        //then
        assertThat(cartItem.getAmount()).isEqualTo(4);
    }
}