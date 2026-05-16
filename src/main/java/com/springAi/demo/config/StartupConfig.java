package com.springAi.demo.config;

import com.springAi.demo.service.VectorEmbeddingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StartupConfig {
    @Bean
    CommandLineRunner run(VectorEmbeddingService service) {
        return args -> {
            service.embedSchemaDocument();
        };
    }
}