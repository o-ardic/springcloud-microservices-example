package com.oardic.api.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * {GlobalFilter} implementation to centrally log the incoming requests.
 *
 * @author Okan ARDIC
 */
@Component
@Slf4j
public class LoggingFilter implements GlobalFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		String clientHost = request.getRemoteAddress().getHostName();
		log.info("Request received from '{}' to '{}'", clientHost, request.getPath());

		return chain.filter(exchange);
	}
}
