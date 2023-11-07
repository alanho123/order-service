package com.polarbookshop.orderservice.book;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author : Jason Ho
 * @since : 2023/11/7
 */
@Component
public class BookClient {
  private static final String BOOK_ROOT_API = "/books/";
  private final WebClient webClient;

  public BookClient(WebClient webClient) {
    this.webClient = webClient;
  }

  public Mono<Book> getBookByIsbn(String isbn) {
    return webClient
        .get()
        .uri(BOOK_ROOT_API + isbn)
        .retrieve()
        .bodyToMono(Book.class);
  }
}