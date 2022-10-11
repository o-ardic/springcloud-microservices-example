package com.oardic.userservice.model;

import lombok.*;

import java.util.List;

/**
 * User entity.
 *
 * @author Okan ARDIC
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User {
	private Long id;
	private String name;
	private List<Order> orders;
}
