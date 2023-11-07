package com.polarbookshop.orderservice.config;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URI;

/**
 * @author : Jason Ho
 * @since : 2023/11/7
 */
@ConfigurationProperties(prefix = "polar")
public record ClientProperties(
    @NotNull
    URI catalogServiceUri
) {
}
