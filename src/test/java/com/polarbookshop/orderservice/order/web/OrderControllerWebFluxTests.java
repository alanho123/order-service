package com.polarbookshop.orderservice.order.web;

import com.polarbookshop.orderservice.order.domain.Order;
import com.polarbookshop.orderservice.order.domain.OrderService;
import com.polarbookshop.orderservice.order.domain.OrderStatus;
import com.polarbookshop.orderservice.web.OrderController;
import com.polarbookshop.orderservice.web.OrderRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

/**
 * @author : Jason Ho
 * @since : 2023/11/7
 */
@WebFluxTest(OrderController.class)
public class OrderControllerWebFluxTests {

  @Autowired
  private WebTestClient webTestClient;

  @MockBean
  private OrderService orderService;

  @Test
  void whenBookNotAvailableThenRejectOrder() {
    var orderRequest = new OrderRequest("1234567891", 3);
    var expectedOrder = OrderService.buildRejectedOrder(orderRequest.isbn(), orderRequest.quantity());
    BDDMockito
        .given(orderService.submitOrder(orderRequest.isbn(), orderRequest.quantity()))
        .willReturn(Mono.just(expectedOrder));
    webTestClient
        .post()
        .uri("/orders/")
        .bodyValue(orderRequest)
        .exchange()
        .expectStatus().is2xxSuccessful()
        .expectBody(Order.class).value(order -> {
          Assertions.assertThat(order).isNotNull();
          Assertions.assertThat(order.status()).isEqualTo(OrderStatus.REJECTED);
        });
  }
}
