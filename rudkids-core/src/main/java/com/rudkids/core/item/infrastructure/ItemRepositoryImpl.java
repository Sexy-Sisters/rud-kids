package com.rudkids.core.item.infrastructure;

import com.rudkids.core.item.domain.Item;
import com.rudkids.core.item.domain.ItemRepository;
import com.rudkids.core.item.exception.ItemNotFoundException;
import com.rudkids.core.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepository {
    private final JpaItemRepository itemRepository;

    @Override
    public void save(Item item) {
        itemRepository.save(item);
    }

    @Override
    public Item get(UUID id) {
        return itemRepository.findById(id)
            .orElseThrow(ItemNotFoundException::new);
    }

    @Override
    public void delete(Item item) {
        itemRepository.delete(item);
    }

    @Override
    public Page<Item> get(Product product, Pageable pageable) {
        return itemRepository.findByProduct(product, pageable);
    }
}