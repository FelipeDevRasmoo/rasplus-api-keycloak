package com.client.api.rasmooplus.dto;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class KeycloakOAuthDto {

    private final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

    public static KeycloakOAuthDto builder() {
        return new KeycloakOAuthDto();
    }

    public KeycloakOAuthDto clientId(String clientId) {
        params.add("client_id", clientId);
        return this;
    }

    public KeycloakOAuthDto clientSecret(String clientSecret) {
        params.add("client_secret", clientSecret);
        return this;
    }

    public KeycloakOAuthDto grantType(String grantType) {
        params.add("grant_type", grantType);
        return this;
    }

    public KeycloakOAuthDto username(String username) {
        params.add("username", username);
        return this;
    }

    public KeycloakOAuthDto password(String password) {
        params.add("password", password);
        return this;
    }

    public KeycloakOAuthDto refreshToken(String refreshToken) {
        params.add("refresh_token", refreshToken);
        return this;
    }

    public MultiValueMap<String, String> build() {
        return params;
    }


}
