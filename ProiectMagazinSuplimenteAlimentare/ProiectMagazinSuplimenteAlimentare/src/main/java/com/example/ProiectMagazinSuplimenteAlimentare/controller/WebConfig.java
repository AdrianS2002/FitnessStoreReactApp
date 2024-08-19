package com.example.ProiectMagazinSuplimenteAlimentare.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 *  WebConfig class is used to configure the web.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Allow all origins for testing
                .allowedMethods("*") // Allow all methods
                .allowedHeaders("*"); // Allow all headers
    }
}
