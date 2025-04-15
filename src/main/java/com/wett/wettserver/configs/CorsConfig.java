package com.wett.wettserver.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Apply to all endpoints
                    .allowedOrigins("http://localhost:8081", "http://localhost:8080")  // Allow Vue.js frontend
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allowed methods
                    .allowedHeaders("*")  // Allow all headers
                    .allowCredentials(true); // Required for Cookies/JWT
            }
        };
    }
}