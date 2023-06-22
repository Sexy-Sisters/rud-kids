package com.rudkids.core.common.fixtures;

import com.rudkids.core.cart.domain.CartItemRepository;
import com.rudkids.core.cart.domain.CartRepository;
import com.rudkids.core.cart.dto.CartRequest;
import com.rudkids.core.cart.infrastructure.JpaCartItemRepository;
import com.rudkids.core.cart.infrastructure.JpaCartRepository;
import com.rudkids.core.cart.service.CartService;
import com.rudkids.core.common.ServiceTest;
import com.rudkids.core.item.domain.*;
import com.rudkids.core.item.infrastructure.JpaItemRepository;
import com.rudkids.core.user.domain.PhoneNumber;
import com.rudkids.core.user.domain.SocialType;
import com.rudkids.core.user.domain.User;
import com.rudkids.core.user.domain.UserName;
import com.rudkids.core.user.infrastructure.JpaUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@ServiceTest
public class CartServiceFixtures {

    @Autowired
    protected CartService cartService;

    @Autowired
    protected CartRepository cartRepository;

    @Autowired
    protected JpaCartRepository jpaCartRepository;

    @Autowired
    protected CartItemRepository cartItemRepository;

    @Autowired
    protected JpaCartItemRepository jpaCartItemRepository;

    @Autowired
    protected JpaUserRepository userRepository;

    @Autowired
    protected JpaItemRepository itemRepository;

    protected User user;
    protected Item item;

    protected CartRequest.AddCartItem CART_아이템_요청;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .email("namse@gmail.com")
                .name(UserName.create("남세"))
                .age(18)
                .gender("MALE")
                .phoneNumber(PhoneNumber.create("01029401509"))
                .socialType(SocialType.GOOGLE)
                .build();
        userRepository.save(user);

        item = Item.builder()
                .name(Name.create("No.1", "남바완"))
                .price(Price.create(3_000))
                .quantity(Quantity.create(1_000))
                .itemBio(ItemBio.create("소개글입니다~"))
                .limitType(LimitType.LIMITED)
                .build();

        ItemImage image = ItemImage.create(item, "path", "url");
        item.addImage(image);
        itemRepository.save(item);

        //저장된 itemId 넣음
        CART_아이템_요청 = CartRequest.AddCartItem.builder()
                .itemId(item.getId())
                .optionGroups(List.of(
                    CartRequest.AddCartItemOptionGroup.builder()
                                .name("사이즈")
                                .option(new CartRequest.AddCartItemOption("M", 1000))
                                .build(),
                    CartRequest.AddCartItemOptionGroup.builder()
                                .name("색깔")
                                .option(new CartRequest.AddCartItemOption("파랑", 500))
                                .build()
                        ))
                .amount(2)
                .build();
    }
}