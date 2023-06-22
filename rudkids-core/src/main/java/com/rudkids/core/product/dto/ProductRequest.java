package com.rudkids.core.product.dto;

import com.rudkids.core.image.dto.ImageRequest;

import java.util.List;

public class ProductRequest {

    public record Create(
        String title,
        String productBio,
        ImageRequest.Create frontImage,
        ImageRequest.Create backImage,
        List<ImageRequest.Create> bannerImages
    ) {}

    public record Update(
        String title,
        String productBio,
        ImageRequest.Create frontImage,
        ImageRequest.Create backImage
    ) {}

    public record ChangeStatus(String productStatus) {}
}
