package com.client.api.rasmooplus.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

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


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorize -> {
                            authorize.requestMatchers( AUTH_SWAGGER_LIST);
                            authorize.requestMatchers(HttpMethod.GET, "/subscription-type").permitAll();
                            authorize.requestMatchers(HttpMethod.POST, "/user").permitAll();
                            authorize.requestMatchers(HttpMethod.POST, "/payment/process").permitAll();
                            authorize.requestMatchers(HttpMethod.POST, "/auth").permitAll();
                    authorize.requestMatchers(HttpMethod.POST, "/auth/refresh-token").permitAll();
                            authorize.requestMatchers("/auth/recovery-code/*").permitAll();
                            authorize.anyRequest().authenticated();
                        }
                )
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();

    }

}
