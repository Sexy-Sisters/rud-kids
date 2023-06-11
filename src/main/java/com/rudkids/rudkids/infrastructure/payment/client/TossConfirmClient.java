package com.rudkids.rudkids.infrastructure.payment.client;

import com.rudkids.rudkids.infrastructure.payment.dto.PaymentConfirmRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "TossConfirmClient", url = "https://api.tosspayments.com/v1/payments/confirm")
public interface TossConfirmClient {

    @PostMapping
    void post(
        @RequestHeader HttpHeaders headers,
        @RequestBody PaymentConfirmRequest request
    );
}
