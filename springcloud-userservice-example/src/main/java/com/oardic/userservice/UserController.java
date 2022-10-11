package com.oardic.userservice;

import com.oardic.userservice.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Sample controller for {@link User} related operations.
 *
 * @author Okan ARDIC
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private OrderServiceFeignProxy orderServiceProxy;

	private final Map<Long, User> users = Map.of(
		1L, User.builder().name("Joe").id(1L).build(),
		2L, User.builder().name("William").id(2L).build(),
		3L, User.builder().name("Jack").id(3L).build(),
		4L, User.builder().name("Averell").id(4L).build());

	@GetMapping
	public ResponseEntity<List<User>> getUsers() {
		return ResponseEntity.ok(new ArrayList<>(users.values()));
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getUser(@PathVariable long userId) {
		log.info("getUser request received for user ID {}", userId);
		User user = users.get(userId);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		user.setOrders(orderServiceProxy.getUserOrders(userId));
		return ResponseEntity.ok(user);
	}
}
