package com.rudkids.rudkids.domain.item.service;

import com.rudkids.rudkids.domain.item.ItemCommand;
import com.rudkids.rudkids.domain.item.ItemInfo;

import java.util.List;
import java.util.UUID;

public interface ItemService {
    void registerItem(ItemCommand.RegisterItemRequest command, UUID productId);
    ItemInfo.Detail findItemDetail(UUID id);
    String openItem(UUID id);
    String closeItem(UUID id);
}
