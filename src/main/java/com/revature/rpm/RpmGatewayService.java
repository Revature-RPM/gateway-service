package com.revature.rpm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
public class RpmGatewayService {

	public static void main(String[] args) {
		SpringApplication.run(RpmGatewayService.class, args);
	}
	
	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/login")
						     .uri("lb://AUTH-SERVICE")
						     .id("login"))
				
				.route(r -> r.path("/users/**")
							 .filters(f -> f.addRequestHeader("X-RPM-Gateway", "test-value")
									 		.hystrix(c -> c.setName("hystrix-fallback")
									 					   .setFallbackUri("forward:/fallback")))
							 .uri("lb://AUTH-SERVICE")
							 .id("users"))
				
				.route(r -> r.path("/projects/**")
							 .uri("lb://PROJECT-SERVICE")
							 .id("projects"))
				
				.build();
	}
	
}
