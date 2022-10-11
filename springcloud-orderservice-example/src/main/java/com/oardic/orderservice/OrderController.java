package com.oardic.orderservice;

import com.oardic.orderservice.model.Order;
import com.oardic.orderservice.model.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Sample Order Controller
 *
 * @author Okan ARDIC
 */
@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private ProductServiceFeignProxy productServiceProxy;

	private final Map<Long, Order> orders = Map.of(
		1L, Order.builder().id(10L).userId(1).price(100).orderTime(Instant.now()).build(),
		2L, Order.builder().id(11L).userId(1).price(1250).orderTime(Instant.now()).build(),
		3L, Order.builder().id(12L).userId(2).price(75).orderTime(Instant.now()).build(),
		4L, Order.builder().id(13L).userId(3).price(85).orderTime(Instant.now()).build());

	public OrderController() {
		// Populate order details
		List<OrderDetail> orderDetails = new ArrayList<>();
		Order order = orders.get(1L);
		orderDetails.add(
			OrderDetail.builder().id(100L).orderId(order.getId()).quantity(3).unitPrice(0.7).productId(1000).build());
		orderDetails.add(
			OrderDetail.builder().id(101L).orderId(order.getId()).quantity(1).unitPrice(8).productId(1001).build());
		orderDetails.add(
			OrderDetail.builder().id(102L).orderId(order.getId()).quantity(2).unitPrice(3.5).productId(1002).build());
		order.setOrderDetails(orderDetails);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Order>> getUserOrders(@PathVariable long userId) {
		log.info("getUserOrders request received for user ID {}", userId);
		List<Order> userOrders = orders.values().stream()
		                               .filter(order -> order.getUserId() == userId)
		                               .collect(Collectors.toList());
		return ResponseEntity.ok(userOrders);
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<List<OrderDetail>> getOrderDetails(@PathVariable long orderId) {
		log.info("getOrderDetails request received for order ID {}", orderId);
		Order order = orders.get(orderId);
		if (order == null) {
			return ResponseEntity.notFound().build();
		}
		order.getOrderDetails()
		     .forEach(orderDetail -> orderDetail.setProduct(
			     productServiceProxy.getProduct(orderDetail.getProductId())));
		return ResponseEntity.ok(order.getOrderDetails());
	}
}
