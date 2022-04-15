package com.stock.sweet.sweetstockapi.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

<<<<<<< HEAD:sweet-stock-api/src/main/java/com/stock/sweet/sweetstockapi/security/CorsConfiguration.java

=======
>>>>>>> 807508ad068ac32a36b2475516752b9c68bb6b39:sweet-stock-api/src/main/java/com/stock/sweet/sweetstockapi/CorsConfiguration.java
@Configuration

public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*");
    }
}
