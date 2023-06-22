package com.rudkids.core.auth.dto;

public class AuthResponse {

    public record Uri(String oAuthUri) {}

    public record AccessAndRefreshToken(String accessToken, String refreshToken) {}

    public record AccessToken(String accessToken) {}
}
