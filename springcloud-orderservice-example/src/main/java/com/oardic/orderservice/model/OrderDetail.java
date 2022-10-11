package com.oardic.orderservice.model;

import lombok.*;

/**
 * Order detail entity.
 *
 * @author Okan ARDIC
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OrderDetail {

	private Long id;
	private long orderId;
	private long productId;
	private Product product;
	private double quantity;
	private double unitPrice;
}
