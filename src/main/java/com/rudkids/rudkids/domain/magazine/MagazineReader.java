package com.rudkids.rudkids.domain.magazine;

import com.rudkids.rudkids.domain.magazine.domain.Magazine;

import java.util.UUID;

public interface MagazineReader {
    Magazine getMagazine(UUID magazineId);
}
