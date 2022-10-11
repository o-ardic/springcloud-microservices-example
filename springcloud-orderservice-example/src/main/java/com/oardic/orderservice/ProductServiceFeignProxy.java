package com.oardic.orderservice;

import com.oardic.orderservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Sample OpenFeign proxy that will act as a REST client to make calls to Order Service.
 *
 * @author Okan ARDIC
 */
@FeignClient("${services.productService.name}")
public interface ProductServiceFeignProxy {

	@GetMapping("/products/{productId}")
	Product getProduct(@PathVariable long productId);
}
