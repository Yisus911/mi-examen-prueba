package com.examen.prueba.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Examen de Prueba - Teléfonos",
                description = "Aplicación que provee un crud de teléfonos",
                termsOfService = "https://www.linkedin.com/in/jes%C3%BAs-p%C3%A9rez-cruz-b8295a77",
                version = "1.0.0",
                contact = @Contact(
                        name = "Jesús Pérez Cruz",
                        url = "https://www.linkedin.com/in/jes%C3%BAs-p%C3%A9rez-cruz-b8295a77/",
                        email = "jesuspc_905@hotmail.com"
                ),
                license = @License(
                        name = "Licencia de prueba de aplicativo de telefonos",
                        url = "https://www.linkedin.com/in/jes%C3%BAs-p%C3%A9rez-cruz-b8295a77/"
                )
        ),
        servers = {
                @Server(
                        description = "DEV SERVER",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "PROD SERVER",
                        url = "http://localhost:8080"
                )
        },
        security = @SecurityRequirement(
                name = "Security Token"
        )
)
public class SwaggerConfig {}