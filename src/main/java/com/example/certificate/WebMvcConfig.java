package com.example.certificate;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "http://localhost:5174","http://165.229.125.93", "http://165.229.125.93:8000",
                        "http://localhost:8080/gpttest")
                .allowedMethods("OPTIONS","GET","POST","PUT","DELETE")
                .allowedHeaders("*");
    }

}
