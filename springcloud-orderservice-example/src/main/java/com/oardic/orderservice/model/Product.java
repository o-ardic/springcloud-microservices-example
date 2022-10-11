package com.oardic.orderservice.model;

import lombok.*;

/**
 * Product entity.
 *
 * @author Okan ARDIC
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Product {

	private Long id;
	private String name;
	private long quantity;
}
