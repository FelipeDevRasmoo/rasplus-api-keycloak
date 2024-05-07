package com.client.api.rasmooplus.service.impl;

import com.client.api.rasmooplus.component.HttpComponent;
import com.client.api.rasmooplus.dto.KeycloakOAuthDto;
import com.client.api.rasmooplus.dto.LoginDto;
import com.client.api.rasmooplus.exception.BadRequestException;
import com.client.api.rasmooplus.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final String REFRESH_TOKEN = "refresh_token";
    private static final String PASSWORD = "password";

    @Value("${keycloak.auth-server-uri}")
    private String keycloakUri;

    @Value("${keycloak.credentials.client-id}")
    private String clientId;

    @Value("${keycloak.credentials.client-secret}")
    private String clientSecret;

    @Autowired
    private HttpComponent httpComponent;

    @Override
    public String auth(LoginDto dto) {
        try {
            MultiValueMap<String, String> keycloakOAuth = KeycloakOAuthDto.builder()
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .username(dto.getUsername())
                    .password(dto.getPassword())
                    .grantType(PASSWORD)
                    .build();
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(keycloakOAuth, httpComponent.httpHeaders());
            ResponseEntity<String> response = httpComponent.restTemplate().postForEntity(
                    keycloakUri + "/protocol/openid-connect/token",
                    request,
                    String.class
            );
            return response.getBody();
        } catch (Exception e) {
            throw new BadRequestException("Erro ao formatar token - "+e.getMessage());
        }
    }

    @Override
    public String refreshToken(String refreshToken) {
        try {
            MultiValueMap<String, String> keycloakOAuth = KeycloakOAuthDto.builder()
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .refreshToken(refreshToken)
                    .grantType(REFRESH_TOKEN)
                    .build();
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(keycloakOAuth, httpComponent.httpHeaders());
            ResponseEntity<String> response = httpComponent.restTemplate().postForEntity(
                    keycloakUri + "/protocol/openid-connect/token",
                    request,
                    String.class
            );
            return response.getBody();
        } catch (Exception e) {
            throw new BadRequestException("Erro ao formatar token - "+e.getMessage());
        }
    }
}
