package com.rudkids.api.common.fixtures.payment;

import com.rudkids.core.payment.dto.PaymentRequest;

import java.util.UUID;

public class PaymentFixturesAndDocs {

    public static final String PAYMENT_DEFAULT_URL = "/api/v1/payment";
    public static final UUID ORDER_ID = UUID.randomUUID();
    private static final int AMOUNT = 5000;

    public static PaymentRequest.Confirm PAYMENT_결제_승인_요청() {
        return new PaymentRequest.Confirm("paymentKey", ORDER_ID, AMOUNT);
    }

    public static PaymentRequest.Cancel PAYMENT_결제_취소_요청() {
        return new PaymentRequest.Cancel(
            "paymentKey",
            "cancelReason",
            "bankCode",
            "refundAccountNumber",
            "refundAccountHolderName"
        );
    }
}
