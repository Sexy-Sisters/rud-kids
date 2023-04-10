package com.rudkids.rudkids.infrastructure.item.itemOption;

import com.rudkids.rudkids.domain.item.domain.itemOption.ItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemOptionRepository extends JpaRepository<ItemOption, UUID> {
}