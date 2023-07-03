package com.rudkids.core.auth.infrastructure.client.kakao;

import com.rudkids.core.auth.dto.OAuthRequest;
import com.rudkids.core.auth.dto.OAuthResponse;
import com.rudkids.core.auth.infrastructure.OAuthProvider;
import com.rudkids.core.auth.infrastructure.OAuthProviderType;
import com.rudkids.core.auth.infrastructure.client.TokenParser;
import com.rudkids.core.auth.dto.AuthUser;
import com.rudkids.core.auth.service.OAuthClientManager;
import com.rudkids.core.config.properties.KakaoProperties;
import com.rudkids.core.auth.exception.OAuthException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@OAuthProvider(OAuthProviderType.KAKAO)
public class KakaoOAuthClientManager implements OAuthClientManager {
    private final KakaoProperties properties;
    private final KakaoTokenClient kakaoTokenClient;
    private final TokenParser tokenParser;

    @Override
    public AuthUser.OAuth getOAuthUser(String code, String redirectUri) {
        OAuthResponse.KakaoToken tokenResponse = requestToken(code, redirectUri);
        var userInfo = tokenParser.parse(tokenResponse.getIdToken(), OAuthResponse.KakaoUserInfo.class);
        return new AuthUser.OAuth(userInfo.email(), userInfo.nickname(), userInfo.picture());
    }

    private OAuthResponse.KakaoToken requestToken(String code, String redirectUri) {
        OAuthRequest.KakaoToken tokenRequest = OAuthRequest.KakaoToken.builder()
            .clientId(properties.getClientId())
            .code(code)
            .redirectUri(redirectUri)
            .build();

        try {
            return kakaoTokenClient.get(tokenRequest);
        } catch (FeignException e) {
            log.error(e.getMessage());
            throw new OAuthException();
        }
    }
}
