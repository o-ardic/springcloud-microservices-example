package com.oardic.userservice.model;

import lombok.*;

import java.time.Instant;

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

	private Long userId;

	private double price;

	private Instant orderTime;
}
