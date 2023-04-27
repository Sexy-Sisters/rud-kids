package com.rudkids.rudkids.common.fixtures.product;

import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import com.rudkids.rudkids.domain.product.ProductInfo;
import com.rudkids.rudkids.domain.product.domain.ProductStatus;
import com.rudkids.rudkids.interfaces.image.dto.ImageRequest;
import com.rudkids.rudkids.interfaces.product.dto.ProductRequest;
import com.rudkids.rudkids.interfaces.product.dto.ProductResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public class ProductControllerFixtures {

    public static final String PRODUCT_DEFAULT_URL = "/api/v1/product";
    public static final UUID 프로덕트_아이디 = UUID.randomUUID();
    public static final String 프로덕트_제목 = "Strange Drugstore";
    public static final String 프로덕트_소개글 = "약쟁이가 약팝니다~~~~";
    public static final String 프로덕트_앞_이미지 = "https://~";
    public static final String 프로덕트_뒤_이미지 = "https://~";
    public static final ProductStatus 프로덕트_상태 = ProductStatus.OPEN;

    public static ProductRequest.Create PRODUCT_등록_요청() {
        return new ProductRequest.Create(프로덕트_제목, 프로덕트_소개글, 이미지(), 이미지());
    }

    private static ImageRequest 이미지() {
        return new ImageRequest("image", "image.jpg");
    }

    public static ProductInfo.Main PRODUCT_MAIN_INFO() {
        return new ProductInfo.Main(
            프로덕트_아이디,
            프로덕트_제목,
            프로덕트_앞_이미지,
            프로덕트_뒤_이미지,
            프로덕트_상태
        );
    }

    public static ProductResponse.Main PRODUCT_MAIN_RESPONSE() {
        return new ProductResponse.Main(
            프로덕트_아이디,
            프로덕트_제목,
            프로덕트_앞_이미지,
            프로덕트_뒤_이미지,
            프로덕트_상태
        );
    }

    public static List<ProductInfo.Main> PRODUCT_리스트_조회_응답() {
        return List.of(
            PRODUCT_MAIN_INFO()
        );
    }

    public static ProductInfo.Detail PRODUCT_상세조회_INFO() {
        return ProductInfo.Detail.builder()
            .title(프로덕트_제목)
            .bio(프로덕트_소개글)
            .frontImageUrl(프로덕트_앞_이미지)
            .backImageUrl(프로덕트_뒤_이미지)
            .items(
                List.of(ITEM_리스트_조회_INFO())
            )
            .build();
    }

    private static ProductInfo.ProductItem ITEM_리스트_조회_INFO() {
        return ProductInfo.ProductItem.builder()
            .itemId(UUID.randomUUID())
            .name("아이템")
            .price(1000)
            .imageUrls(List.of("url1", "url2", "url3"))
            .itemStatus(ItemStatus.SELLING)
            .build();
    }

    public static ProductResponse.Detail PRODUCT_상세조회_RESPONSE() {
        return ProductResponse.Detail.builder()
            .title(프로덕트_제목)
            .bio(프로덕트_소개글)
            .frontImageUrl(프로덕트_앞_이미지)
            .backImageUrl(프로덕트_뒤_이미지)
            .items(
                List.of(ITEM_리스트_조회_RESPONSE())
            )
            .build();
    }

    private static ProductResponse.ProductItem ITEM_리스트_조회_RESPONSE() {
        return ProductResponse.ProductItem.builder()
            .itemId(UUID.randomUUID())
            .name("아이템")
            .price(1000)
            .imageUrls(List.of("url1", "url2"))
            .itemStatus(ItemStatus.SELLING)
            .build();
    }

    public static ProductRequest.Update PRODUCT_수정_요청() {
        return new ProductRequest.Update(프로덕트_제목, 프로덕트_소개글, 이미지(), 이미지());
    }

    public static ProductRequest.ChangeStatus PRODUCT_상태_변경_요청() {
        return new ProductRequest.ChangeStatus(ProductStatus.CLOSED);
    }
}
