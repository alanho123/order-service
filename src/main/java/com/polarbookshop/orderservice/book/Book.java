package com.polarbookshop.orderservice.book;

/**
 * @author : Jason Ho
 * @since : 2023/11/7
 */
public record Book(
    String isbn,
    String title,
    String author,
    Double price
) {
}
