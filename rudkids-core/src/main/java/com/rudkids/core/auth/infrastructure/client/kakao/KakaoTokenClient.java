package com.rudkids.core.auth.infrastructure.client.kakao;

import com.rudkids.core.auth.infrastructure.dto.kakao.KakaoTokenRequest;
import com.rudkids.core.auth.infrastructure.dto.kakao.KakaoTokenResponse;
import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "KakaoTokenClient", url = "https://kauth.kakao.com/oauth/token")
public interface KakaoTokenClient {

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    KakaoTokenResponse get(KakaoTokenRequest request) throws FeignException;
}