package com.polarbookshop.orderservice.order.domain;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @author : Jason Ho
 * @since : 2023/10/27
 */
public interface OrderRepository extends ReactiveCrudRepository<Order, Long> {
}
