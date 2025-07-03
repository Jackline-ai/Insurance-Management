package com.test.claimsmanagement;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Insurance management for medical REST API Documentation",
                description = "Insurance management for medical REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Insurance Top Gurus",
                        email = "insurancetopgurus@gmail.com",
                        url = "https://www.insurancetopgurus.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.insurancetopgurus.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Insurance management for medical REST API Documentation",
                url = "https://www.insurancetopgurus.com/swagger-ui.html"
        )
)

public class ClaimsManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClaimsManagementApplication.class, args);
    }
}