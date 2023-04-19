package com.rudkids.rudkids.common.fixtures.product;

import com.rudkids.rudkids.domain.product.ProductInfo;
import com.rudkids.rudkids.interfaces.product.dto.ProductRequest;

import java.util.List;
import java.util.UUID;

public class ProductControllerFixtures {

    public static final String PRODUCT_DEFAULT_URL = "/api/v1/product";
    public static final UUID  프로덕트_아이디 = UUID.randomUUID();
    public static final String 프로덕트_제목 = "Strange Drugstore";
    public static final String 프로덕트_소개글 = "약쟁이가 약팝니다~~~~";

    public static ProductRequest.Register PRODUCT_등록_요청() {
        return new ProductRequest.Register(프로덕트_제목, 프로덕트_소개글);
    }

    public static List<ProductInfo.Main> PRODUCT_리스트_조회_응답() {
        return List.of(
            new ProductInfo.Main(프로덕트_아이디, 프로덕트_제목, 프로덕트_소개글)
        );
    }

    public static ProductInfo.Detail PRODUCT_상세_조회_응답() {
        return ProductInfo.Detail.builder()
            .title(프로덕트_제목)
            .bio(프로덕트_소개글)
            .build();
    }
}
