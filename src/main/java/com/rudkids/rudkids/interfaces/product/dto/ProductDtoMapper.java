package com.rudkids.rudkids.interfaces.product.dto;

import com.rudkids.rudkids.domain.product.ProductCommand;
import com.rudkids.rudkids.domain.product.ProductInfo;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper {

    public ProductCommand.RegisterRequest toCommand(ProductRequest.Register request) {
        return ProductCommand.RegisterRequest.builder()
            .title(request.getTitle())
            .productBio(request.getProductBio())
            .frontImage(request.getFrontImage())
            .backImage(request.getBackImage())
            .build();
    }

    public ProductResponse.Main toResponse(ProductInfo.Main info) {
        return ProductResponse.Main.builder()
            .productId(info.productId())
            .title(info.title())
            .frontImageUrl(info.frontImageUrl())
            .backImageUrl(info.backImageUrl())
            .status(info.status())
            .build();
    }

    public ProductResponse.Detail toResponse(ProductInfo.Detail info) {
        var items = info.items().stream()
            .map(this::toResponse)
            .toList();

        return ProductResponse.Detail.builder()
            .title(info.title())
            .bio(info.bio())
            .frontImageUrl(info.frontImageUrl())
            .backImageUrl(info.backImageUrl())
            .items(items)
            .build();
    }

    private ProductResponse.ProductItem toResponse(ProductInfo.ProductItem info) {
        return ProductResponse.ProductItem.builder()
            .itemId(info.itemId())
            .name(info.name())
            .price(info.price())
            .imageUrls(info.imageUrls())
            .itemStatus(info.itemStatus())
            .build();
    }
}
