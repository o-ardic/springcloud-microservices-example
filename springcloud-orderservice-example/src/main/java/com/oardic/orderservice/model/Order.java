package com.oardic.orderservice.model;

import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Order entity.
 *
 * @author Okan ARDIC
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Order {

	private Long id;

	private long userId;

	private double price;

	private Instant orderTime;

	private List<OrderDetail> orderDetails;
}
