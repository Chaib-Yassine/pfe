package org.sid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import com.netflix.discovery.DiscoveryClient;

@SpringBootApplication
public class GetewayServiceApplication {
	
	//@Bean
	RouteLocator gatewayRoutes(RouteLocatorBuilder builder){

	return builder.routes()

	.route(r->r.path("/custemers/**").uri("lb://CUSTOMER-SERVICE") .id("r1"))
	.route(r->r.path("/products/**").uri("lb://INVENTORY-SERVICE") .id("r2"))
	.build();

	}
	@Bean
	DiscoveryClientRouteDefinitionLocator dynamicRoute(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp) {
		return new DiscoveryClientRouteDefinitionLocator(rdc, dlp);
	}

	public static void main(String[] args) {
		SpringApplication.run(GetewayServiceApplication.class, args);
	}

}
