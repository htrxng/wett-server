package com.wett.wettserver.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Disable CSRF for API requests
            .cors(cors -> cors.disable())  // Disable CORS (since we configured it separately)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.PUT,
                    "/v1/product/**"
                ).permitAll()
                .requestMatchers(HttpMethod.POST,
                    "/v1/product/**"
                ).permitAll()
                .requestMatchers(HttpMethod.DELETE,
                    "/v1/product/**"
                ).permitAll()
                .requestMatchers(HttpMethod.OPTIONS,
                    "/v1/product/**"
                ).permitAll()
                .requestMatchers(HttpMethod.GET,
                    "/v1/**",
                    "/v1/categories/**",
                    "/v1/products/**",
                    "/v1/product/**"
                ).permitAll()  // Allow this endpoint
                .anyRequest().authenticated()  // Secure other endpoints
            );
        return http.build();
    }
}
