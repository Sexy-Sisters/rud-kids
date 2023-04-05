package com.rudkids.rudkids.domain.cart.domain;

import com.rudkids.rudkids.domain.item.domain.*;
import com.rudkids.rudkids.domain.user.domain.Age;
import com.rudkids.rudkids.domain.user.domain.Gender;
import com.rudkids.rudkids.domain.user.domain.SocialType;
import com.rudkids.rudkids.domain.user.domain.User;
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
                .name("남세")
                .age(Age.create(18))
                .gender(Gender.toEnum("MALE"))
                .socialType(SocialType.GOOGLE)
                .build();
        Cart cart = Cart.create(user);

        //when
        Item item = Item.builder()
                .name(Name.create("No.1"))
                .price(Price.create(2_000))
                .quantity(Quantity.create(1_000))
                .itemBio(ItemBio.create("소개글입니다~"))
                .limitType(LimitType.LIMITED)
                .build();
        CartItem cartItem = CartItem.create(cart, item);
        cartItem.addAmount(4);

        //then
        assertThat(cartItem.getCartItemPrice()).isEqualTo(8000);
    }
}