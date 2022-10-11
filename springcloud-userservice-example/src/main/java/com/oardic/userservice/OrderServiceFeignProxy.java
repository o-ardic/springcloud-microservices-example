package com.oardic.userservice;

import com.oardic.userservice.model.Order;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Sample OpenFeign proxy that will act as a REST client to make calls to Order Service.
 *
 * @author Okan ARDIC
 */
@FeignClient("${services.orderService.name}")
public interface OrderServiceFeignProxy {

	@GetMapping("/orders/user/{userId}")
	@Retry(name = "order-service", fallbackMethod = "getUserOrdersFallbackForRetry")
	@CircuitBreaker(name = "order-service", fallbackMethod = "getUserOrdersFallbackForCircuitBreaker")
	List<Order> getUserOrders(@PathVariable long userId);

	/**
	 * Retry fallback method for {@link #getUserOrders(long)} method.
	 *
	 * @return a list containing an order with an ID equal to -2.
	 */
	default List<Order> getUserOrdersFallbackForRetry(Exception e) {
		return List.of(Order.builder().id(-2L).build());
	}

	/**
	 * Circuit Breaker fallback method for {@link #getUserOrders(long)} method.
	 *
	 * @return a list containing an order with an ID equal to -1.
	 */
	default List<Order> getUserOrdersFallbackForCircuitBreaker(CallNotPermittedException e) {
		return List.of(Order.builder().id(-1L).build());
	}
}
