package com.oardic.productservice;

import com.oardic.productservice.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

/**
 * Sample Order Controller
 *
 * @author Okan ARDIC
 */
@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

	private final Map<Long, Product> products = Map.of(
		1000L, Product.builder().id(1000L).name("pencil").quantity(500).unitPrice(0.7).build(),
		1001L, Product.builder().id(1001L).name("book").quantity(100).unitPrice(8).build(),
		1002L, Product.builder().id(1002L).name("notebook").quantity(220).unitPrice(3.5).build(),
		1003L, Product.builder().id(1003L).name("briefcase").quantity(30).unitPrice(22).build());

	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable long productId) {
		log.info("getProduct request received for product ID {}", productId);
		Product product = products.get(productId);
		if (product == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(product);
	}

	@GetMapping
	public ResponseEntity<Collection<Product>> getProducts() {
		return ResponseEntity.ok(products.values());
	}
}
