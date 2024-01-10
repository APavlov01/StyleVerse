package com.styleverse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@SpringBootApplication
@EnableSwagger2
public class StyleVerseApplication {
    public static void main(String[] args) {
        SpringApplication.run(StyleVerseApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer () {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedHeaders("*")
                        .allowedOrigins("http://127.0.0.1:5173", "http://127.0.0.1:5173/", "http://localhost:3306s/").allowCredentials(true);
            }
        };
    }
}
