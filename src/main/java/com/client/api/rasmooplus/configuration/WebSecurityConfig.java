package com.client.api.rasmooplus.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    private static final String[] AUTH_SWAGGER_LIST = {
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/v2/api-docs/**",
            "/swagger-resources/**"
    };

    @Value("${keycloak.auth-server-uri}")
    private String keycloakUri;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorize -> {
                            authorize.requestMatchers(AUTH_SWAGGER_LIST).permitAll();
                            authorize.requestMatchers(HttpMethod.POST, "/auth").permitAll();
                            authorize.requestMatchers("/auth/**").hasAnyAuthority("CLIENT_READ_WRITE", "ADMIN_READ", "ADMIN_WRITE");
                            authorize.requestMatchers(HttpMethod.GET, "/subscription-type").hasAnyAuthority("CLIENT_READ_WRITE", "ADMIN_READ", "ADMIN_WRITE");
                            authorize.requestMatchers(HttpMethod.POST, "/payment/process").hasAnyAuthority("CLIENT_READ_WRITE", "ADMIN_READ", "ADMIN_WRITE");
                            authorize.requestMatchers(HttpMethod.POST, "/user").hasAnyAuthority("CLIENT_READ_WRITE", "ADMIN_READ", "ADMIN_WRITE");
                            authorize.requestMatchers("/user/**").hasAnyAuthority("USER_READ", "USER_WRITE", "ADMIN_READ", "ADMIN_WRITE");
                            authorize.anyRequest().hasAnyAuthority("ADMIN_READ", "ADMIN_WRITE");
                        }
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.decoder(jwtDecoder())))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();

    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(keycloakUri + "/realms/REALM_RASPLUS_API/protocol/openid-connect/certs").build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        Converter<Jwt, Collection<GrantedAuthority>> jwtCollectionConverter = jwt -> {
            Map<String, Object> resourcesAccess = jwt.getClaim("realm_access");
            Collection<String> roles = (Collection<String>) resourcesAccess.get("roles");
            return roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        };
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtCollectionConverter);
        return jwtAuthenticationConverter;
    }

}
