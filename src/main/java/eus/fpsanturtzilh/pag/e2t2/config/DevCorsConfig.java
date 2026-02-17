package eus.fpsanturtzilh.pag.e2t2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class DevCorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        System.out.println("CORS KONFIGURAZIOA KARGATU DA");
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")     // todos los endpoints
                        .allowedOrigins("*")   // cualquier origen
                        .allowedMethods("*")   // GET, POST, PUT, DELETE, OPTIONS
                        .allowedHeaders("*");  // cualquier header
            }
        };
    }
}