package com.pe.insalud.backend.shared.interfaces.rest.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configura CORS para permitir solicitudes desde otros orígenes.
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * Permite CORS para todos los endpoints, métodos y encabezados.
     *
     * @param registry configuración de CORS
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*");
    }
}
