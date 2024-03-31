package ru.gb.apiGateAway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGateAwayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGateAwayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("Payment", r -> r.path("/work_wear/**")
                        .uri("http://localhost:8082/"))
                .route("Payment", r -> r.path("/work_wear_issued/**")
                        .uri("http://localhost:8082/"))
                .route("Payment", r -> r.path("/work_wear_total/**")
                        .uri("http://localhost:8082/"))
                .route("Payment", r -> r.path("/work_wear_order/**")
                        .uri("http://localhost:8082/"))
                .route("Payment", r -> r.path("/employee/**")
                        .uri("http://localhost:8081/"))
                .route("Warehouse", r -> r.path("/work_shoes/**")
                        .uri("http://localhost:8083/"))
                .route("Warehouse", r -> r.path("/work_shoes_issued/**")
                        .uri("http://localhost:8083/"))
                .route("Warehouse", r -> r.path("/work_shoes_total/**")
                        .uri("http://localhost:8083/"))
                .route("Warehouse", r -> r.path("/work_shoes_order/**")
                        .uri("http://localhost:8083/"))
                .build();
    }

}
