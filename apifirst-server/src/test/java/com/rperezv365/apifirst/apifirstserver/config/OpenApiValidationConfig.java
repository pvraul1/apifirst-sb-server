package com.rperezv365.apifirst.apifirstserver.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.atlassian.oai.validator.OpenApiInteractionValidator;
import com.atlassian.oai.validator.springmvc.OpenApiValidationFilter;
import com.atlassian.oai.validator.springmvc.OpenApiValidationInterceptor;

import jakarta.servlet.Filter;

/**
 * OpenApiValidationConfig
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 08/01/2025 - 09:26
 * @since 1.17
 */
@Configuration
public class OpenApiValidationConfig {

    @Bean
    public Filter validationFilter() {

        return new OpenApiValidationFilter(
                true, // enable request validation
                true  // enable response validation
        );
    }

    @Bean
    public WebMvcConfigurer openAPIValidationInterceptor() throws IOException {

        ClassPathResource resource = new ClassPathResource("openapi.yaml");

        Path yamlPath = Path.of(resource.getURI());
        String yamlContent = Files.readString(yamlPath);

        OpenApiInteractionValidator validator = OpenApiInteractionValidator
                .createForInlineApiSpecification(yamlContent)
                .build();

        /*
        OpenApiInteractionValidator validator = OpenApiInteractionValidator.createForSpecificationUrl("https://api.redocly.com/registry/bundle/spring-framework-guru/API%20First%20With%20Spring%20Boot%20-%20Development/v1/openapi.yaml?branch=development")
                .build();
         */

        OpenApiValidationInterceptor interceptor = new OpenApiValidationInterceptor(validator);

        return new WebMvcConfigurer() {

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(interceptor);
            }
        };

    }

}
