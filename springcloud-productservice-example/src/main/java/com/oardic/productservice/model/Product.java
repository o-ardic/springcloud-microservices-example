package com.oardic.productservice.model;

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
	private double unitPrice;
}
