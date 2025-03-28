package com.security.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpHeaders;

@OpenAPIDefinition(
        info = @Info(
                title = "Seguridad Jwt",
                description = "Aplicación que provee seguridad Jwt",
                termsOfService = "https://www.linkedin.com/in/jes%C3%BAs-p%C3%A9rez-cruz-b8295a77",
                version = "1.0.0",
                contact = @Contact(
                        name = "Jesús Pérez Cruz",
                        url = "https://www.linkedin.com/in/jes%C3%BAs-p%C3%A9rez-cruz-b8295a77/",
                        email = "jesuspc_905@hotmail.com"
                ),
                license = @License(
                        name = "Licencia de prueba de aplicativo de seguridad Jwt",
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
@SecurityScheme(
        name = "Security Token",
        description = "Access Token For My API",
        type = SecuritySchemeType.HTTP,
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {}