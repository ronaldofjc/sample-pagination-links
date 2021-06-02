package br.com.sample.pagination.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI greetingApi() {
        return new OpenAPI()
                .info(info());
    }

    private Info info() {
        return new Info()
                .title("API Sample Pagination and Links Hateoas")
                .description("This API processes a list of 50 items, applying pagination and filling in the links following the hateoas pattern")
                .version("1.0.0");
    }
}