package com.rudkids.api.common.fixtures;

import com.rudkids.core.delivery.dto.DeliveryResponse;
import com.rudkids.core.item.domain.ItemStatus;
import com.rudkids.core.order.domain.OrderStatus;
import com.rudkids.core.order.domain.PayMethod;
import com.rudkids.core.order.dto.OrderRequest;
import com.rudkids.core.order.dto.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static com.rudkids.api.common.ControllerTest.pageResponseFieldsWith;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class OrderControllerFixtures {

    public static final String ORDER_DEFAULT_URL = "/api/v1/order";
    public static final UUID orderId = UUID.randomUUID();
    public static final OrderStatus orderStatus = OrderStatus.INIT;
    private static final PayMethod payMethod = PayMethod.TOSS;
    private static final ZonedDateTime createdAt = ZonedDateTime.now();
    private static final UUID deliveryId = UUID.randomUUID();

    private static final String receiverName = "이규진";
    private static final String receiverPhone = "010-5476-5574";
    private static final String receiverAddress1 = "부산시 사하구 윤공단로123";
    private static final String receiverAddress2 = "나는 몰라용~";
    private static final String receiverZipcode = "494999";
    private static final String etcMessage = "나는 2024년 총 매출 35억을 달성했고 다낭으로 여행왔다. 나는 2024년 페라리를 샀다.";

    public static OrderResponse.Receipt receipt = OrderResponse.Receipt.builder()
        .totalPrice(50000)
        .items(
            List.of(
                OrderResponse.ReceiptItemInfo.builder()
                    .itemId(UUID.randomUUID())
                    .name("상품명")
                    .price(10000)
                    .amount(5)
                    .itemStatus(ItemStatus.SELLING)
                    .optionGroups(
                        List.of(
                            OrderResponse.ReceiptOptionGroup.builder()
                                .name("name")
                                .optionName("optionName")
                                .build()
                        )
                    )
                    .build()
            )
        )
        .build();

    public static OrderRequest.Create ORDER_주문_요청() {
        return new OrderRequest.Create(payMethod, deliveryId);
    }

    public static OrderRequest.ChangeStatus ORDER_상태변경_요청() {
        return new OrderRequest.ChangeStatus("DELIVERY_COMPLETE");
    }

    public static OrderResponse.Detail ORDER_상세조회_응답() {
        var delivery = DeliveryResponse.Info.builder()
            .receiverName(receiverName)
            .receiverPhone(receiverPhone)
            .zipCode(receiverZipcode)
            .address1(receiverAddress1)
            .address2(receiverAddress2)
            .message(etcMessage)
            .build();

        return OrderResponse.Detail.builder()
            .orderId(orderId)
            .deliveryFragment(delivery)
            .orderStatus(orderStatus)
            .payMethod(payMethod)
            .receipt(receipt)
            .build();
    }

    public static Page<OrderResponse.Main> ORDER_전체_조회_INFO() {
        return new PageImpl<>(List.of(
            new OrderResponse.Main(orderId, createdAt)
        ));
    }

    public static List<OrderResponse.Main> ORDER_주문내역_조회_INFO() {
        return List.of(new OrderResponse.Main(orderId, createdAt));
    }

    public static List<FieldDescriptor> ORDER_상세조회_응답_필드() {
        return List.of(fieldWithPath("orderId")
                .type(JsonFieldType.STRING)
                .description("주문 ID"),

            fieldWithPath("deliveryFragment.receiverName")
                .type(JsonFieldType.STRING)
                .description("수신자"),

            fieldWithPath("deliveryFragment.receiverPhone")
                .type(JsonFieldType.STRING)
                .description("전화번호"),

            fieldWithPath("deliveryFragment.zipCode")
                .type(JsonFieldType.STRING)
                .description("우편번호"),

            fieldWithPath("deliveryFragment.address1")
                .type(JsonFieldType.STRING)
                .description("주소1"),

            fieldWithPath("deliveryFragment.address2")
                .type(JsonFieldType.STRING)
                .description("주소2"),

            fieldWithPath("deliveryFragment.message")
                .type(JsonFieldType.STRING)
                .description("배송시 요청사항"),

            fieldWithPath("payMethod")
                .type(JsonFieldType.STRING)
                .description("결제수단"),

            fieldWithPath("orderStatus")
                .type(JsonFieldType.STRING)
                .description("주문 상태"),

            fieldWithPath("receipt.totalPrice")
                .type(JsonFieldType.NUMBER)
                .description("총 주문 가격"),

            fieldWithPath("receipt.items[].itemId")
                .type(JsonFieldType.STRING)
                .description("아이템 ID"),

            fieldWithPath("receipt.items[].name")
                .type(JsonFieldType.STRING)
                .description("상품명"),

            fieldWithPath("receipt.items[].price")
                .type(JsonFieldType.NUMBER)
                .description("아이템 가격"),

            fieldWithPath("receipt.items[].amount")
                .type(JsonFieldType.NUMBER)
                .description("주문한 개수"),

            fieldWithPath("receipt.items[].optionGroups[].name")
                .type(JsonFieldType.STRING)
                .description("옵션 그룹 이름"),

            fieldWithPath("receipt.items[].optionGroups[].optionName")
                .type(JsonFieldType.STRING)
                .description("옵션 이름"),

            fieldWithPath("receipt.items[].itemStatus")
                .type(JsonFieldType.STRING)
                .description("아이템 상태")
        );
    }

    public static List<FieldDescriptor> ORDER_주문내역_조회_응답_필드() {
        return List.of(
            fieldWithPath("[].orderId")
                .type(JsonFieldType.STRING)
                .description("주문 ID"),

            fieldWithPath("[].createdAt")
                .type(JsonFieldType.STRING)
                .description("주문한 시간")
        );
    }

    public static List<FieldDescriptor> ORDER_주문_요청_필드() {
        return List.of(
            fieldWithPath("payMethod")
                .type(JsonFieldType.STRING)
                .description("결제수단"),

            fieldWithPath("deliveryId")
                .type(JsonFieldType.STRING)
                .description("배송 id")
        );
    }

    public static List<FieldDescriptor> ORDER_배송정보_수정_요청_필드() {
        return List.of(
            fieldWithPath("receiverName")
                .type(JsonFieldType.STRING)
                .description("수신자"),

            fieldWithPath("receiverPhone")
                .type(JsonFieldType.STRING)
                .description("전화번호"),

            fieldWithPath("receiverAddress1")
                .type(JsonFieldType.STRING)
                .description("주소1"),

            fieldWithPath("receiverAddress2")
                .type(JsonFieldType.STRING)
                .description("주소2"),

            fieldWithPath("receiverZipcode")
                .type(JsonFieldType.STRING)
                .description("우편번"),

            fieldWithPath("etcMessage")
                .type(JsonFieldType.STRING)
                .description("배송시 요청사항")
        );
    }

    public static List<FieldDescriptor> ORDER_전체_주문_조회_응답_필드() {
        return pageResponseFieldsWith(
            List.of(
                fieldWithPath("content.[].orderId")
                    .type(JsonFieldType.STRING)
                    .description("주문 ID"),

                fieldWithPath("content.[].createdAt")
                    .type(JsonFieldType.STRING)
                    .description("주문한 시간")
            )
        );
    }
}