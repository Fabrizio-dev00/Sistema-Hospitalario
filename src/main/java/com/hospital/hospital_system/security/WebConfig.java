package com.hospital.hospital_system.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica a todos los endpoints de la API
                .allowedOrigins("*") // [IMPORTANTE] Permite CUALQUIER origen (para desarrollo local)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite los métodos necesarios para el CRUD
                .allowedHeaders("*"); // Permite cualquier cabecera
    }
}
