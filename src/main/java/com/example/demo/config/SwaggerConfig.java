package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // Define the server URL for the workspace environment
        Server server = new Server();
        server.setUrl("https://9081.pro604cr.amypo.ai/");
        server.setDescription("Workspace Server");

        return new OpenAPI()
                .info(new Info()
                        .title("Smart Inventory Restock Predictor API")
                        .version("1.0")
                        .description("REST API documentation for inventory tracking and restock predictions"))
                .servers(List.of(server))
                // Enable global security requirement for the documentation
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        // Define the JWT Bearer security scheme 
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .name("bearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}