package com.rushabh.book_author.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Book Author Api",
               summary = "Book Author api with details of all young authors",
                description = "This API allows users to perform CRUD operations on both books and authors. It leverages Spring Boot for rapid development and PostgreSQL as the database for data storage. Integration testing ensures the reliability and correctness of the API endpoints."
        )
)
public class SwaggerOpenApiConfig {
}
