package com.rudkids.rudkids.interfaces.item.dto;

import com.rudkids.rudkids.domain.item.domain.*;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ItemRequest {

    @Builder
    public record RegisterItem(
        String name,
        String itemBio,
        int price,
        int quantity,
        LimitType limitType,
        List<MultipartFile> images,
        List<ItemRequest.RegisterItemOptionGroup> itemOptionGroupList
    ) {}

    @Builder
    public record RegisterItemOptionGroup(
        Integer ordering,
        String itemOptionGroupName,
        List<ItemRequest.RegisterItemOption> itemOptionList
    ) {}

    @Builder
    public record RegisterItemOption(
        Integer ordering,
        String itemOptionName,
        int itemOptionPrice
    ) {}

    @Builder
    public record Update(
        String name,
        String itemBio,
        int price,
        int quantity,
        LimitType limitType
    ) {
    }

    public record ChangeStatus(ItemStatus itemStatus) {
    }
}
