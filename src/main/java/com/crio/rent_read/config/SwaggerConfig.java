package com.crio.rent_read.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Rent Read API",
                version = "1.0.0",
                description = "API documentation for Rent Read application - a book rental management system",
                contact = @Contact(
                        name = "Rent Read Support",
                        url = "https://rentread.example.com"
                )
        ),
        servers = {
                @Server(
                        url = "http://localhost:8081",
                        description = "Local Server"
                )
        }
)
@SecurityScheme(
        name = "BearerAuth", // ✅ FIXED NAME
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        description = "Enter JWT token",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {
}
