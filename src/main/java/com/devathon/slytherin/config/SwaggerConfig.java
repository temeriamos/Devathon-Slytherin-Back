package com.devathon.slytherin.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Devathon Equipo 1",
                description = "API para Devathon Equipo 1",
                termsOfService = "",
                version = "1.0.0",
                contact = @Contact(
                        name = "Equipo Numero 1",
                        url = "https://www.devathon1",
                        email = "support@devathon1.com"
                ),
                license = @License(
                        name = "Standard Apache License Version 2.0 for Fintech",
                        url = "https://www.apache.org/licenses/LICENSE-2.0",
                        identifier = "Apache-2.0"
                )
        ),
        servers = {
                @Server(
                        description = "Local Server",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Production Server",
                        url = "https://"
                )
        }
)
public class SwaggerConfig {}