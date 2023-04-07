package com.rudkids.rudkids.common.fixtures.item;

import com.github.f4b6a3.ulid.UlidCreator;
import com.rudkids.rudkids.domain.item.ItemInfo;
import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import com.rudkids.rudkids.domain.item.domain.LimitType;
import com.rudkids.rudkids.interfaces.item.dto.ItemRequest;

import java.util.List;
import java.util.UUID;

public class ItemControllerFixtures {

    public static final String ITEM_DEFAULT_URL = "/api/v1/item";
    public static final UUID 프로덕트_아이디 = UlidCreator.getMonotonicUlid().toUuid();
    public static final UUID 아이템_아이디 = UlidCreator.getMonotonicUlid().toUuid();
    public static final String 아이템_이름 = "No.1";
    public static final String 아이템_소개글 = "소개합니다~~~~";
    public static final int 아이템_가격 = 10_000;
    public static final int 아이템_수량 = 100;
    public static final LimitType 아이템_수량_한정_여부 = LimitType.LIMITED;
    public static final ItemStatus 아이템_상태 = ItemStatus.ON_SALES;
    public static final String 아이템_상태_판매중 = ItemStatus.ON_SALES.name();
    public static final String 아이템_상태_솔드아웃 = ItemStatus.END_OF_SALES.name();

    public static ItemRequest.RegisterItem ITEM_등록_요청() {
        return ItemRequest.RegisterItem.builder()
            .productId(프로덕트_아이디)
            .name(아이템_이름)
            .price(아이템_가격)
            .quantity(아이템_수량)
            .limitType(아이템_수량_한정_여부)
            .itemOptionGroupList(List.of(itemOptionGroup_사이즈))
            .build();
    }

    private static final ItemRequest.RegisterItemOptionGroup itemOptionGroup_사이즈 = ItemRequest.RegisterItemOptionGroup.builder()
        .ordering(1)
        .itemOptionGroupName("사이즈")
        .itemOptionList(List.of(
            ItemRequest.RegisterItemOption.builder()
                .ordering(1)
                .itemOptionName("S")
                .itemOptionPrice(0)
                .build(),
            ItemRequest.RegisterItemOption.builder()
                .ordering(2)
                .itemOptionName("M")
                .itemOptionPrice(0)
                .build(),
            ItemRequest.RegisterItemOption.builder()
                .ordering(3)
                .itemOptionName("L")
                .itemOptionPrice(1000)
                .build()
        )).build();

    public static List<ItemInfo.Main> ITEM_리스트_조회_응답() {
        return List.of(
            ItemInfo.Main.builder()
                .name(아이템_이름)
                .price(아이템_가격)
                .itemStatus(아이템_상태)
                .build()
        );
    }

    public static ItemInfo.Detail ITEM_상세정보_조회_응답() {
        return ItemInfo.Detail.builder()
            .name(아이템_이름)
            .price(아이템_가격)
            .itemBio(아이템_소개글)
            .quantity(아이템_수량)
            .limitType(아이템_수량_한정_여부)
            .itemStatus(아이템_상태)
            .build();
    }
}