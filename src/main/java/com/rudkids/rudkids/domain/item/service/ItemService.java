package com.rudkids.rudkids.domain.item.service;

import com.rudkids.rudkids.domain.item.ItemCommand;
import com.rudkids.rudkids.domain.item.ItemInfo;
import com.rudkids.rudkids.domain.item.domain.ItemStatus;

import java.util.UUID;

public interface ItemService {
    UUID create(ItemCommand.CreateItemRequest command, UUID productId, UUID userId);
    ItemInfo.Detail find(UUID itemId);
    ItemStatus changeItemStatus(UUID itemId, ItemStatus userId, UUID itemStatus);
    void update(ItemCommand.UpdateRequest command, UUID itemId, UUID userId);
    void delete(UUID itemId, UUID userId);
}