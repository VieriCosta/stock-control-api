package com.vieri.stockcontrol.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI stockControlOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Stock Control API")
                        .description("API for managing products, raw materials and production planning")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Vieri Costa")
                                .email("viericosta@gmail.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Repository")
                        .url("https://github.com/seu-repo"));
    }
}