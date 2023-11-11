package com.example.apigateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.RewritePathGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class SwaggerFilter extends RewritePathGatewayFilterFactory {

    // This method is required to be implemented by the RewritePathGatewayFilterFactory
    @Override
    public GatewayFilter apply(Config config) {
        // The returned GatewayFilter defines the behavior of the filter
        return (exchange, chain) -> {
            // Get the original request
            var request = exchange.getRequest();

            // Modify the request path to "/api-docs"
            var modifiedRequest = request
                    .mutate()
                    .path("/api-docs")
                    .build();

            // Chain the modified request to the next filter in the chain
            return chain.filter(exchange.mutate().request(modifiedRequest).build());
        };
    }
}
