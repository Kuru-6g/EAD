package com.example.apigateway;

import com.example.apigateway.filters.AuthenticationPrefilter;
import com.example.apigateway.filters.SwaggerFilter;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
@OpenAPIDefinition(info = @Info(title = "E-Commerce-Shop API"))
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(
            final RouteLocatorBuilder builder,
            final SwaggerFilter swaggerFilter) {
        return builder.routes()

                .route("user-service-api-docs", r -> r.path("/user-service/api-docs")
                        .filters(f -> f.filter(swaggerFilter.apply(new SwaggerFilter.Config())))
                        .uri("lb://user-service"))
                .route("inventory-service-api-docs", r -> r.path("/inventory-service/api-docs")
                        .filters(f -> f.filter(swaggerFilter.apply(new SwaggerFilter.Config())))
                        .uri("lb://inventory-service"))
                .route("order-service-api-docs", r -> r.path("/order-service/api-docs")
                        .filters(f -> f.filter(swaggerFilter.apply(new SwaggerFilter.Config())))
                        .uri("lb://order-service"))
                .route("cart-service-api-docs", r -> r.path("/cart-service/api-docs")
                        .filters(f -> f.filter(swaggerFilter.apply(new SwaggerFilter.Config())))
                        .uri("lb://cart-service"))
                .build();
    }
}
