package com.client.api.rasmooplus.service.impl;

import com.client.api.rasmooplus.component.HttpComponent;
import com.client.api.rasmooplus.dto.LoginDto;
import com.client.api.rasmooplus.exception.BadRequestException;
import com.client.api.rasmooplus.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Value("${keycloak.auth-server-uri}")
    private String keycloakUri;

    @Value("${keycloak.credentials.client-id}")
    private String clientId;

    @Value("${keycloak.credentials.client-secret}")
    private String clientSecret;

    @Value("${keycloak.credentials.grant-type}")
    private String grantType;

    @Autowired
    private HttpComponent httpComponent;

    @Override
    public String auth(LoginDto dto) {
        try {
            return "";
        } catch (Exception e) {
            throw new BadRequestException("Erro ao formatar token - "+e.getMessage());
        }
    }
}
